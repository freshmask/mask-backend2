package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.entity.*;
import com.mask.mask.exception.*;
import com.mask.mask.repository.ClaimPARepository;
import com.mask.mask.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Optional;

@Service
@Transactional
public class ClaimPAServiceImpl implements ClaimPAService {
    @Autowired
    ClaimPARepository claimPARepository;

    @Autowired
    TransactionPAService transactionPAService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    UsersService usersService;

    @Autowired
    CategoryPAService categoryPAService;

    @Autowired
    PackagePAService packagePAService;

    @Override
    public String saveClaimPA(ClaimPA claimPA) {
        float submissionValue = 0.00f;
        TransactionPA transactionPAInitiate = transactionPAService.getransactionPAByTransactionId(claimPA.getTransaction().getId());
        CategoryPA categoryPAInitiate = categoryPAService.getCategoryPAById(transactionPAInitiate.getCategoryPA().getCategoryId());
        PackagePA packagePAInitiate = packagePAService.getPackagePAById(categoryPAInitiate.getPackagePA().getPaId());
        if (transactionPAInitiate.getStatusPolis().equalsIgnoreCase("aktif")){
            if(!transactionPAInitiate.getIsClaim().equalsIgnoreCase("true")){
                if (claimPA.getLossCause().equalsIgnoreCase("Lengan kanan mulai dari sendi bahu")) {
                    submissionValue = packagePAInitiate.getCompensation() * 0.6f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Lengan kiri mulai dari sendi bahu")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.5f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Lengan kanan mulai dari atasnya sendi siku")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.5f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Lengan kiri mulai dari atasnya sendi siku")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.4f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Tangan kanan mulai dari atasnya pergelangan tangan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.4f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Tangan kiri mulai dari atasnya pergelangan tangan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.3f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Satu kaki mulai dari lutut sampai pangkal paha")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.5f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Satu kaki mulai dari mata kaki sampai lutut")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.25f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Ibu jari tangan kanan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.15f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Ibu jari tangan kiri")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.1f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari telunjuk tangan kanan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.1f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari telunjuk tangan kiri")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.08f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari kelingking tangan kanan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.08f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari kelingking tangan kiri")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.06f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari tengah atau manis tangan kanan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.05f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Jari tengah atau manis tangan kiri")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.04f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Satu ibu jari kaki")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.08f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Satu jari kaki lainnya")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.05f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Sebelah mata")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.5f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Pendengaran pada kedua belah telinga")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.5f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Pendengaran pada sebelah telinga")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.25f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Sebelah daun telinga secara keseluruhan")){
                    submissionValue = packagePAInitiate.getCompensation() * 0.05f;
                } else if (claimPA.getLossCause().equalsIgnoreCase("Cacat Tetap Keseluruhan")){
                    submissionValue = packagePAInitiate.getCompensation();
                } else if (claimPA.getLossCause().equalsIgnoreCase("Kematian")){
                    submissionValue = packagePAInitiate.getCompensation();
                }
                System.out.println(claimPA.getClaimSubmission());
                if (claimPA.getClaimSubmission() <= submissionValue) {
                    Transaction transaction = transactionService.getTransactionById(claimPA.getTransaction().getId());
                    TransactionPA transactionPA = transactionPAService.getTransactionPAById(transaction.getTransactionPA().getTrxpaId());
                    transactionPA.setStatusClaim("Proses Persetujuan");
                    transactionPA.setIsClaim("true");
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    claimPA.setName(users.getName());
                    claimPA.setEmail(users.getEmail());
                    transaction.setVersion(transaction.getVersion() + 1);
                    claimPARepository.save(claimPA);
                    return "Sukses";
                } else if (claimPA.getClaimSubmission() == null){
                    Transaction transaction = transactionService.getTransactionById(claimPA.getTransaction().getId());
                    TransactionPA transactionPA = transactionPAService.getTransactionPAById(transaction.getTransactionPA().getTrxpaId());
                    transactionPA.setStatusClaim("Proses Persetujuan");
                    transactionPA.setIsClaim("true");
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    claimPA.setName(users.getName());
                    claimPA.setEmail(users.getEmail());
                    claimPA.setClaimSubmission(submissionValue);
                    transaction.setVersion(transaction.getVersion() + 1);
                    transactionService.updateTransaction(transaction.getId(), transaction);
                    transactionPAService.updateTransactionPA(transactionPA);
                    claimPARepository.save(claimPA);
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
    public void updateClaimPA(String id, ClaimPA claimPA) {
        ClaimPA claimPA1 = claimPARepository.findById(id).get();
        claimPA1.setName(claimPA.getName());
        claimPA1.setEmail(claimPA.getEmail());
        claimPA1.setReportDate(claimPA.getReportDate());
        claimPA1.setIncidentDate(claimPA.getIncidentDate());
        claimPA1.setLossCause(claimPA.getLossCause());
        claimPA1.setIncidentLocation(claimPA.getIncidentLocation());
        claimPA1.setClaimSubmission(claimPA.getClaimSubmission());
        claimPA1.setClaimApproval(claimPA.getClaimApproval());
        claimPA1.setTransaction(claimPA.getTransaction());
        claimPA1.setMedicalCertificateName(claimPA.getMedicalCertificateName());
        claimPA1.setMedicalExpensesName(claimPA.getMedicalExpensesName());
        claimPA1.setDeathCertificateName(claimPA.getDeathCertificateName());
        claimPARepository.save(claimPA1);
    }

    @Override
    public ClaimPA getClaimPAById(String id) {
        return claimPARepository.findById(id).get();

    }

    @Override
    public Page<ClaimPA> getClaimPAPerPage(Pageable pageable) {
        return claimPARepository.findAll(pageable);
    }

    @Override
    public List<ClaimPA> getClaimPA() {
        return claimPARepository.findAll();
    }

    @Override
    public void deleteClaimPAById(String id) {
        claimPARepository.deleteById(id);
    }

    @Override
    public void updateClaimPAAproved(String id, ClaimPA claimPA) throws IOException, MessagingException {
        ClaimPA claimPA1 = claimPARepository.findById(id).get();
        if(claimPA.getClaimApproval() > claimPA.getClaimSubmission()) {
            throw new NominalExceedException(String.format("Nominal persetujuan yang kamu berikan melebihi tuntutan user"));
        } else {
            claimPA1.setClaimApproval(claimPA.getClaimApproval());
            Transaction transaction = transactionService.getTransactionById(claimPA1.getTransaction().getId());
            claimPA1.getTransaction().getTransactionPA().setStatusClaim("disetujui");
            transaction.setVersion(transaction.getVersion() + 1);
            claimPARepository.save(claimPA1);
            String description = "";
            emailSenderCore.sendNotifClaimPA(claimPA1, description);
        }


    }

    @Override
    public void updateClaimPARejected(String id, String description) throws IOException, MessagingException {
        ClaimPA claimPA1 = claimPARepository.findById(id).get();
        Transaction transaction = transactionService.getTransactionById(claimPA1.getTransaction().getId());
        claimPA1.getTransaction().getTransactionPA().setStatusClaim("ditolak");
        transaction.setVersion(transaction.getVersion() + 1);
        claimPARepository.save(claimPA1);
        emailSenderCore.sendNotifClaimPA(claimPA1, description);
    }

}
