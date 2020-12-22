package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.dto.ClaimPARSearchDTO;
import com.mask.mask.entity.*;
import com.mask.mask.exception.*;
import com.mask.mask.repository.ClaimPARRepository;
import com.mask.mask.specification.ClaimPARSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import java.util.Optional;

@Service
public class ClaimPARServiceImpl implements ClaimPARService {

    @Autowired
    ClaimPARRepository claimPARRepository;

    @Autowired
    TransactionPARService transactionPARService;

    @Autowired
    UsersService usersService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    CustomerPARService customerPARService;


    @Override
    public String addClaimPAR(ClaimPAR claimPAR) {
        float submissionValue = 0.00f;
        TransactionPAR transactionPAR = transactionPARService.getTransactionPARByTransactionId(claimPAR.getTransaction().getId());
        CustomerPAR customerPAR = customerPARService.getCustomerPARByTrxparId(transactionPAR.getTrxparId());
        if (transactionPAR.getStatusPolis().equalsIgnoreCase("aktif")) {
            if (!transactionPAR.getIsClaim().equalsIgnoreCase("true")) {
                if (claimPAR.getBuildingType().equalsIgnoreCase("ya")) {
                    submissionValue = submissionValue + customerPAR.getBuildingCoverageValue();
                }
                if (claimPAR.getMachineType().equalsIgnoreCase("ya")) {
                    submissionValue = submissionValue + customerPAR.getMachineCoverageValue();
                }
                if (claimPAR.getFurnitureType().equalsIgnoreCase("ya")) {
                    submissionValue = submissionValue + customerPAR.getFurnitureCoverageValue();
                }

                if (claimPAR.getClaimSubmission() <= submissionValue) {
                    Transaction transaction = transactionService.getTransactionById(claimPAR.getTransaction().getId());
                    TransactionPAR transactionPAR1 = transactionPARService.getTransactionPARById(transaction.getTransactionPAR().getTrxparId());
                    transactionPAR1.setIsClaim("true");
                    transactionPAR1.setStatusClaim("Proses Persetujuan");
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    claimPAR.setName(users.getName());
                    claimPAR.setEmail(users.getEmail());
                    transaction.setVersion(transaction.getVersion() + 1);
                    transactionService.updateTransaction(transaction.getId(),transaction);
                    transactionPARService.updateTransactionPAR(transactionPAR1);
                    claimPARRepository.save(claimPAR);
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
    public Page<ClaimPAR> getClaimPARInPage(Pageable pageable, ClaimPARSearchDTO claimPARSearchDTO) {
        Specification<ClaimPAR> claimPARSpecification= ClaimPARSpecification.getSpecification(claimPARSearchDTO);
        return claimPARRepository.findAll(claimPARSpecification, pageable);
    }

    @Override
    public List<ClaimPAR> getAllClaimPAR() {
        return claimPARRepository.findAll();
    }

    @Override
    public ClaimPAR getClaimPARById(String claimparId) {
        Optional<ClaimPAR> claimPAROptional = claimPARRepository.findById(claimparId);;
        if (claimPAROptional.isPresent()){
            return claimPAROptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE, ClaimPAR.class,claimparId));
    }

    @Override
    public void editClaimPAR(String claimparId, ClaimPAR claimPAR) {
        ClaimPAR newClaimPAR = claimPARRepository.findById(claimparId).get();
        newClaimPAR.setName(claimPAR.getName());
        newClaimPAR.setEmail(claimPAR.getEmail());
        newClaimPAR.setReportDate(claimPAR.getReportDate());
        newClaimPAR.setIncidentDate(claimPAR.getIncidentDate());
        newClaimPAR.setLossCause(claimPAR.getLossCause());
        newClaimPAR.setIncidentLocation(claimPAR.getIncidentLocation());
        newClaimPAR.setFurnitureType(claimPAR.getFurnitureType());
        newClaimPAR.setClaimSubmission(claimPAR.getClaimSubmission());
        newClaimPAR.setClaimApproval(claimPAR.getClaimApproval());
        newClaimPAR.setLossReportName(claimPAR.getLossReportName());
        claimPARRepository.save(newClaimPAR);
    }

    @Override
    public void deleteClaimPARById(String claimparId) {
        claimPARRepository.deleteById(claimparId);
    }


    @Override
    public void updateClaimPARApproved(String id, ClaimPAR claimPAR) throws IOException, MessagingException {
        ClaimPAR claimPAR1 = claimPARRepository.findById(id).get();
        if(claimPAR.getClaimApproval() > claimPAR.getClaimSubmission()){
            throw new NominalExceedException(String.format("Nominal persetujuan yang kamu berikan melebihi tuntutan user"));
        } else {
            claimPAR1.setClaimApproval(claimPAR.getClaimApproval());
            Transaction transaction = transactionService.getTransactionById(claimPAR1.getTransaction().getId());
            claimPAR1.getTransaction().getTransactionPAR().setStatusClaim("disetujui");
            transaction.setVersion(transaction.getVersion() + 1);
            claimPARRepository.save(claimPAR1);
            String description = "";
            emailSenderCore.sendNotifClaimPAR(claimPAR1, description);
        }

    }


    @Override
    public void updateClaimPARRejected(String id, String description) throws IOException, MessagingException {
        ClaimPAR claimPAR1 = claimPARRepository.findById(id).get();
        Transaction transaction = transactionService.getTransactionById(claimPAR1.getTransaction().getId());
        claimPAR1.getTransaction().getTransactionPAR().setStatusClaim("ditolak");
        transaction.setVersion(transaction.getVersion() + 1);
        claimPARRepository.save(claimPAR1);
        emailSenderCore.sendNotifClaimPAR(claimPAR1, description);
    }
}
