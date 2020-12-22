package com.mask.mask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mask.mask.entity.ClaimPA;
import com.mask.mask.entity.ClaimTravel;
import com.mask.mask.entity.Transaction;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.ClaimTravelService;
import com.mask.mask.service.ClaimTravelServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
public class ClaimTravelController {
    @Autowired
    ClaimTravelService claimTravelService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${document-claim}")
    String documentClaim;


    @PostMapping("/claimtravel")
    public String saveClaimTravel(@RequestPart (required = false) MultipartFile medicalCertificate,
                                       @RequestPart (required = false) MultipartFile medicalExpenses,
                                       @RequestPart (required = false) MultipartFile deathCertificate,
                                       @RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam Date reportDate,
                                       @RequestParam Date incidentDate,
                                       @RequestParam String lossCause,
                                       @RequestParam String incidentLocation,
                                       @RequestParam Float claimSubmission,
                                       @RequestParam Float claimApproval,
                                       @RequestParam String transaction) throws JsonProcessingException {
        try{
            if ((medicalCertificate != null) && (medicalExpenses != null)) {
                if (deathCertificate != null) {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "TRV-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "TRV-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                    deathCertificate.transferTo(Paths.get(documentClaim, "TRV-SK-" + name + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename())));
                } else {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "TRV-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "TRV-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                }
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        if (deathCertificate != null){
            String medicalCertificateName = StringUtils.cleanPath("TRV-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("TRV-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            String deathCertificateName = StringUtils.cleanPath("TRV-SK-"+name +"."+ FilenameUtils.getExtension(deathCertificate.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimTravel claimTravel = new ClaimTravel(name, email, reportDate, incidentDate, lossCause, incidentLocation, medicalCertificateName, medicalExpensesName, deathCertificateName, claimSubmission, claimApproval, transaction1);
            claimTravelService.addClaimTravel(claimTravel);
            result = "success";
        } else {
            String medicalCertificateName = StringUtils.cleanPath("TRV-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("TRV-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimTravel claimTravel = new ClaimTravel(name, email, reportDate, incidentDate, lossCause, incidentLocation, medicalCertificateName, medicalExpensesName, claimSubmission, claimApproval, transaction1);
            claimTravelService.addClaimTravel(claimTravel);
            result = "success";
        }
        return result;
    }

    @DeleteMapping("/claimtravel")
    public void deleteClaimTravelById(@RequestParam(name = "id") String id) {
        claimTravelService.deleteClaimTravelById(id);
    }

    @PutMapping("/claimtravel/{id}")
    public void updateClaimTravel(@PathVariable String id,
                                  @RequestPart (required = false) MultipartFile medicalCertificate,
                                  @RequestPart (required = false) MultipartFile medicalExpenses,
                                  @RequestPart (required = false) MultipartFile deathCertificate,
                                  @RequestParam String name,
                                  @RequestParam String email,
                                  @RequestParam Date reportDate,
                                  @RequestParam Date incidentDate,
                                  @RequestParam String lossCause,
                                  @RequestParam String incidentLocation,
                                  @RequestParam Float claimSubmission,
                                  @RequestParam Float claimApproval) {
        try{
            if ((medicalCertificate != null) && (medicalExpenses != null)) {
                if (deathCertificate != null) {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "TRV-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "TRV-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                    deathCertificate.transferTo(Paths.get(documentClaim, "TRV-SK-" + name + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename())));
                } else {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "TRV-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "TRV-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                }
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (deathCertificate != null) {
            String medicalCertificateName = StringUtils.cleanPath("TRV-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("TRV-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            String deathCertificateName = StringUtils.cleanPath("TRV-SK-"+name +"."+ FilenameUtils.getExtension(deathCertificate.getOriginalFilename()));
            ClaimTravel claimTravel = new ClaimTravel(name, email, reportDate, incidentDate, lossCause, incidentLocation, medicalCertificateName, medicalExpensesName, deathCertificateName, claimSubmission, claimApproval);
            claimTravelService.editClaimTravel(id, claimTravel);
        } else {
            String medicalCertificateName = StringUtils.cleanPath("TRV-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("TRV-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            ClaimTravel claimTravel = new ClaimTravel(name, email, reportDate, incidentDate, lossCause, incidentLocation, medicalCertificateName, medicalExpensesName, claimSubmission, claimApproval);
            claimTravelService.editClaimTravel(id, claimTravel);
        }
    }

    @GetMapping("/claimtravel")
    public Page<ClaimTravel> getAllClaimTravelPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        return claimTravelService.getClaimTravelInPage(pageable);
    }

    @GetMapping("/claimtravel/list")
    public List<ClaimTravel> getAllClaimTravel(){
        return claimTravelService.getAllClaimTravel();
    }

    @GetMapping("/claimtravel/{id}")
    public ClaimTravel getClaimTravelById(@PathVariable(name = "id") String id) {
        return claimTravelService.getClaimTravelById(id);
    }

    @PutMapping("/approvedTravel/{id}")
    public void updateApprovedTravel (@PathVariable String id, @RequestBody ClaimTravel claimTravel) throws IOException, MessagingException {
        claimTravelService.updateClaimTravelApproved(id, claimTravel);
    }


    @PutMapping("/rejectedTravel/{id}")
    public void updateRejectedTravel (@PathVariable String id, @RequestParam String description) throws IOException, MessagingException {
        claimTravelService.updateClaimTravelRejected(id, description);
    }

}
