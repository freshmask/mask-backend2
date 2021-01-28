package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.entity.*;
import com.mask.mask.exception.*;
import com.mask.mask.repository.ClaimTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ClaimTravelServiceImpl implements ClaimTravelService {
    @Autowired
    ClaimTravelRepository claimTravelRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionTravelService transactionTravelService;

    @Autowired
    UsersService usersService;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    PackageTravelService packageTravelService;


    @Override
    public String addClaimTravel(ClaimTravel claimTravel) {
        TransactionTravel transactionTravel = transactionTravelService.getransactionTravelByTransactionId(claimTravel.getTransaction().getId());
        float submissionValue = 0.00f;
        if (transactionTravel.getStatusPolis().equalsIgnoreCase("aktif")) {
            if (!transactionTravel.getIsClaim().equalsIgnoreCase("true")) {
                if (claimTravel.getLossCause().equalsIgnoreCase("Meninggal Dunia karena Kecelakaan")) {
                    submissionValue = 135000000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Cacat Tetap akibat kecelakaan")) {
                    submissionValue = 135000000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Pembatalan Perjalanan")) {
                    submissionValue = 2025000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Pengurangan Perjalanan")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Penundaan Perjalanan")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Keterlambatan Penerbangan")) {
                    submissionValue = 2025000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Tertinggal Penerbangan")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Pembajakan Pesawat Terbang")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Keterlambatan bagasi")) {
                    submissionValue = 2025000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Kehilangan atau kerusakan bagasi")) {
                    submissionValue = 6750000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Kehilangan dokumen perjalanan atau barang pribadi")) {
                    submissionValue = 1350000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Perawatan medis")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Evakuasi medis darurat")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Pendampingan anak")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Perjalanan salah satu anggota keluarga")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Repatriasi Pemulangan jenazah")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Cidera badan atau kerusakan fisik harta benda pihak ketiga")) {
                    submissionValue = 1350000.00f;
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan pengiriman pesan darurat")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan pencarian bagasi atau dokumen hilang")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan alamat dan nomor telepon Kedutaan")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan 24 jam Tele-Konsultasi medis, Evaluasi dan Rujukan Medis")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan informasi Pra-Perjalanan")) {
                    submissionValue = claimTravel.getClaimSubmission();
                } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan rujukan pengacara dan penterjemah")) {
                    submissionValue = claimTravel.getClaimSubmission();
                }

                if (claimTravel.getClaimSubmission() <= submissionValue) {
                    Transaction transaction = transactionService.getTransactionById(claimTravel.getTransaction().getId());
                    transactionTravel.setIsClaim("true");
                    transactionTravel.setStatusClaim("Proses Persetujuan");
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    claimTravel.setName(users.getName());
                    claimTravel.setEmail(users.getEmail());
                    claimTravel.setClaimSubmission(submissionValue);
                    transaction.setVersion(transaction.getVersion() + 1);
                    transactionService.updateTransaction(transaction.getId(), transaction);
                    transactionTravelService.updateTransactionTravel(transactionTravel.getId(),transactionTravel);
                    claimTravelRepository.save(claimTravel);
                    return "Sukses";
                } else {
                    String subValue = String.format("%.0f", submissionValue);
                    throw new LimitOfCompensationException(String.format("Nilai Tuntutan Anda Melebihi Nilai Maksimal Santunan Sebesar Rp " + subValue));
                }
            } else {
                throw new AlreadyClaimException(String.format("Transaksi ini sudah pernah di klaim"));
            }
        } else {
            throw new CannotClaimCausePolisIsInactive("Klaim Tidak Dapat Dilakukan Karena Status Polis Anda Sudah Tidak Aktif");
        }
    }

    @Override
    public String addClaimTravelByHeir(ClaimTravel claimTravel, String polisId, String identityNumber, String nameOfTheInsured, String emailOfTheInsured) {
        TransactionTravel transactionTravel = transactionTravelService.getTransactionTravelById(polisId);
        CustomerTravel customerTravel = transactionTravel.getCustomerTravel();
        float submissionValue = 0.00f;
        String result = "";
        if (transactionTravel.getCustomerTravel().getHeirName().equalsIgnoreCase(claimTravel.getName())) {
            if (transactionTravel.getCustomerTravel().getHeirEmail().equalsIgnoreCase(claimTravel.getEmail())) {
                if ((customerTravel.getIdentityNo().equalsIgnoreCase(identityNumber)) && (customerTravel.getName().equalsIgnoreCase(nameOfTheInsured))) {
                    if (transactionTravel.getStatusPolis().equalsIgnoreCase("aktif")) {
                        if (!transactionTravel.getIsClaim().equalsIgnoreCase("true")) {
                            if (claimTravel.getLossCause().equalsIgnoreCase("Meninggal Dunia karena Kecelakaan")) {
                                submissionValue = 135000000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Cacat Tetap akibat kecelakaan")) {
                                submissionValue = 135000000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Pembatalan Perjalanan")) {
                                submissionValue = 2025000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Pengurangan Perjalanan")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Penundaan Perjalanan")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Keterlambatan Penerbangan")) {
                                submissionValue = 2025000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Tertinggal Penerbangan")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Pembajakan Pesawat Terbang")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Keterlambatan bagasi")) {
                                submissionValue = 2025000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Kehilangan atau kerusakan bagasi")) {
                                submissionValue = 6750000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Kehilangan dokumen perjalanan atau barang pribadi")) {
                                submissionValue = 1350000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Perawatan medis")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Evakuasi medis darurat")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Pendampingan anak")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Perjalanan salah satu anggota keluarga")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Repatriasi Pemulangan jenazah")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Cidera badan atau kerusakan fisik harta benda pihak ketiga")) {
                                submissionValue = 1350000.00f;
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan pengiriman pesan darurat")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan pencarian bagasi atau dokumen hilang")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan alamat dan nomor telepon Kedutaan")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan 24 jam Tele-Konsultasi medis, Evaluasi dan Rujukan Medis")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan informasi Pra-Perjalanan")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            } else if (claimTravel.getLossCause().equalsIgnoreCase("Layanan rujukan pengacara dan penterjemah")) {
                                submissionValue = claimTravel.getClaimSubmission();
                            }

                            if (claimTravel.getClaimSubmission() <= submissionValue) {
                                Transaction transaction = transactionService.getTransactionById(transactionTravel.getTransaction().getId());
                                transactionTravel.setIsClaim("true");
                                transactionTravel.setStatusClaim("Proses Persetujuan");
                                claimTravel.setClaimSubmission(submissionValue);
                                claimTravel.setTransaction(transaction);
                                transaction.setVersion(transaction.getVersion() + 1);
                                transactionService.updateTransaction(transaction.getId(), transaction);
                                transactionTravelService.updateTransactionTravel(transactionTravel.getId(), transactionTravel);
                                claimTravelRepository.save(claimTravel);
                                result = "Sukses";
                            } else {
                                String subValue = String.format("%.0f", submissionValue);
                                result = "Nilai Tuntutan Anda Melebihi Nilai Maksimal Santunan Sebesar Rp " + subValue;
                            }
                        } else {
                            result = "Polis ini sudah di klaim";
                        }
                    } else {
                        result = "Klaim Tidak Dapat Dilakukan Karena Status Polis Anda Sudah Tidak Aktif";
                    }
                } else {
                    result = "Identitas Tertanggung tidak sesuai dengan polis";
                }
            } else {
                result = "Email pemohon tidak terdaftar sebagai ahli waris sehingga anda tidak memiliki hak untuk mengajukan klaim asuransi";
            }
        } else {
            result = "Nama pemohon tidak terdaftar sebagai ahli waris sehingga anda tidak memiliki hak untuk mengajukan klaim asuransi";
        }
        return result;
    }

    @Override
    public Page<ClaimTravel> getClaimTravelInPage(Pageable pageable) {
        return claimTravelRepository.findAll(pageable);
    }

    @Override
    public List<ClaimTravel> getAllClaimTravel() {
        return claimTravelRepository.findAll();
    }

    @Override
    public ClaimTravel getClaimTravelById(String id) {
        return claimTravelRepository.findById(id).get();
    }

    @Override
    public void editClaimTravel(String id, ClaimTravel claimTravel) {
        ClaimTravel claimTravel1 = claimTravelRepository.findById(id).get();
        claimTravel1.setClaimApproval(claimTravel.getClaimApproval());
        claimTravel1.setClaimSubmission(claimTravel.getClaimSubmission());
        claimTravel1.setMedicalCertificate(claimTravel.getMedicalCertificate());
        claimTravel1.setEmail(claimTravel.getEmail());
        claimTravel1.setName(claimTravel.getName());
        claimTravel1.setIncidentDate(claimTravel.getIncidentDate());
        claimTravel1.setReportDate(claimTravel.getReportDate());
        claimTravel1.setLossCause(claimTravel.getLossCause());
        claimTravel1.setIncidentLocation(claimTravel.getIncidentLocation());
        claimTravelRepository.save(claimTravel1);
    }

    @Override
    public void deleteClaimTravelById(String id) {
        claimTravelRepository.deleteById(id);
    }


    @Override
    public void reviewClaimTravelApproved(String id, ClaimTravel claimTravel) throws IOException, MessagingException {
        ClaimTravel claimTravel1 = claimTravelRepository.findById(id).get();
        claimTravel1.getTransaction().getTransactionTravel().setStatusClaim("data sesuai");
        claimTravelRepository.save(claimTravel1);
    }

    @Override
    public void updateClaimTravelApproved(String id, ClaimTravel claimTravel) throws IOException, MessagingException {
        ClaimTravel claimTravel1 = claimTravelRepository.findById(id).get();
        if(claimTravel.getClaimApproval() > claimTravel.getClaimSubmission()) {
            throw new NominalExceedException(String.format("Nominal persetujuan yang kamu berikan melebihi tuntutan user"));
        } else {
            claimTravel1.setClaimApproval(claimTravel.getClaimApproval());
            Transaction transaction = transactionService.getTransactionById(claimTravel1.getTransaction().getId());
            claimTravel1.getTransaction().getTransactionTravel().setStatusClaim("disetujui");
            transaction.setVersion(transaction.getVersion() + 1);
            claimTravelRepository.save(claimTravel1);
            String description = "";
            emailSenderCore.sendNotifClaimTravel(claimTravel1, description);
        }
    }

    @Override
    public void updateClaimTravelRejected(String id, String description) throws IOException, MessagingException {
        ClaimTravel claimTravel1 = claimTravelRepository.findById(id).get();
        Transaction transaction = transactionService.getTransactionById(claimTravel1.getTransaction().getId());
        claimTravel1.getTransaction().getTransactionTravel().setStatusClaim("ditolak");
        transaction.setVersion(transaction.getVersion() + 1);
        claimTravelRepository.save(claimTravel1);
        emailSenderCore.sendNotifClaimTravel(claimTravel1, description);
    }
}
