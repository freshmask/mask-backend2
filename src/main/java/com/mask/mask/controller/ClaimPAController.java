package com.mask.mask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mask.mask.entity.ClaimPA;
import com.mask.mask.entity.ClaimTravel;
import com.mask.mask.entity.Transaction;
import com.mask.mask.entity.Users;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.ClaimPAService;
import com.mask.mask.service.ClaimPAServiceImpl;
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
public class ClaimPAController {

    @Value("${document-claim}")
    String documentClaim;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClaimPAService claimPAService;

    private static final Logger logger = LoggerFactory.getLogger(ClaimPAController.class);

    @PostMapping("/claimpa")
    public String saveClaimPA(@RequestPart (required = false) MultipartFile medicalCertificate,
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
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                    deathCertificate.transferTo(Paths.get(documentClaim, "PA-SK-" + name + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename())));
                } else {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                }
            } else {
                throw new IncompleteFileException ("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        if (deathCertificate != null){
            String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("PA-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            String deathCertificateName = StringUtils.cleanPath("PA-SK-"+name +"."+ FilenameUtils.getExtension(deathCertificate.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimPA claimPA = new ClaimPA(name, email, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName, deathCertificateName, transaction1);
            claimPAService.saveClaimPA(claimPA);
            result = "Sukses";
        } else {
            String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("PA-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimPA claimPA = new ClaimPA(name, email, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName, transaction1);
            claimPAService.saveClaimPA(claimPA);
            result = "Sukses";
        }
        return result;
    }

    @PostMapping("/claimpaByHeir")
    public String saveClaimPAByHeir(@RequestPart (required = false) MultipartFile medicalCertificate,
                                    @RequestPart (required = false) MultipartFile medicalExpenses,
                                    @RequestPart (required = false) MultipartFile deathCertificate,
                                    @RequestParam String nameOfTheInsured,
                                    @RequestParam String emailOfTheInsured,
                                    @RequestParam String identityNumber,
                                    @RequestParam String heirName,
                                    @RequestParam String heirEmail,
                                    @RequestParam Date reportDate,
                                    @RequestParam Date incidentDate,
                                    @RequestParam String lossCause,
                                    @RequestParam String incidentLocation,
                                    @RequestParam Float claimSubmission,
                                    @RequestParam Float claimApproval,
                                    @RequestParam String polisId) throws JsonProcessingException {
        try{
            if ((medicalCertificate != null) && (medicalExpenses != null)) {
                if (deathCertificate != null) {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                    deathCertificate.transferTo(Paths.get(documentClaim, "PA-SK-" + nameOfTheInsured + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename())));
                } else {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        if ((medicalCertificate != null) && (medicalExpenses != null)) {
            if (deathCertificate != null) {
                String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
                String medicalExpensesName = StringUtils.cleanPath("PA-RBP-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
                String deathCertificateName = StringUtils.cleanPath("PA-SK-" + nameOfTheInsured + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename()));
                ClaimPA claimPA = new ClaimPA(heirName, heirEmail, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName, deathCertificateName);
                result = claimPAService.saveClaimPAByHeir(claimPA, polisId, identityNumber, nameOfTheInsured, emailOfTheInsured);
            } else {
                String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
                String medicalExpensesName = StringUtils.cleanPath("PA-RBP-" + nameOfTheInsured + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
                ClaimPA claimPA = new ClaimPA(heirName, heirEmail, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName);
                result = claimPAService.saveClaimPAByHeir(claimPA, polisId, identityNumber, nameOfTheInsured, emailOfTheInsured);
            }
        } else {
            result = "Dokumen tidak lengkap";
        }
        return result;
    }

    @DeleteMapping("/claimPA")
    public void deleteClaimPAById(@RequestParam(name = "id") String id) {
        claimPAService.deleteClaimPAById(id);
    }

    @PutMapping("/claimPA/{id}")
    public void updateClaimPA(@PathVariable String claimpaId,
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
                              @RequestParam Float claimApproval,
                              @RequestParam String transaction) throws JsonProcessingException {
        try{
            if ((medicalCertificate != null) && (medicalExpenses != null)) {
                if (deathCertificate != null) {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                    deathCertificate.transferTo(Paths.get(documentClaim, "PA-SK-" + name + "." + FilenameUtils.getExtension(deathCertificate.getOriginalFilename())));
                } else {
                    medicalCertificate.transferTo(Paths.get(documentClaim, "PA-SKD-" + name + "." + FilenameUtils.getExtension(medicalCertificate.getOriginalFilename())));
                    medicalExpenses.transferTo(Paths.get(documentClaim, "PA-RBP-" + name + "." + FilenameUtils.getExtension(medicalExpenses.getOriginalFilename())));
                }
            } else {
                throw new IncompleteFileException ("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (deathCertificate != null){
            String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("PA-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            String deathCertificateName = StringUtils.cleanPath("PA-SK-"+name +"."+ FilenameUtils.getExtension(deathCertificate.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimPA claimPA = new ClaimPA(name, email, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName, deathCertificateName, transaction1);
            claimPAService.updateClaimPA(claimpaId, claimPA);
        } else {
            String medicalCertificateName = StringUtils.cleanPath("PA-SKD-" + name + "."+ FilenameUtils.getExtension(medicalCertificate.getOriginalFilename()));
            String medicalExpensesName = StringUtils.cleanPath("PA-RBP-"+name +"."+ FilenameUtils.getExtension(medicalExpenses.getOriginalFilename()));
            Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
            ClaimPA claimPA = new ClaimPA(name, email, reportDate, incidentDate, lossCause, incidentLocation, claimSubmission, claimApproval, medicalCertificateName, medicalExpensesName, transaction1);
            claimPAService.updateClaimPA(claimpaId, claimPA);
        }
    }

    @GetMapping("/claimPA")
    public Page<ClaimPA> getAllClaimPAPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        return claimPAService.getClaimPAPerPage(pageable);
    }

    @GetMapping("/claimPA/list")
    public List<ClaimPA> getAllClaiimPA(){
        return claimPAService.getClaimPA();
    }

    @GetMapping("/claimPA/{id}")
    public ClaimPA getClaimPAById(@PathVariable(name = "id") String id) {
        return claimPAService.getClaimPAById(id);
    }

    @PutMapping("/reviewPA/{id}")
    public void reviewApprovedPA (@PathVariable String id, @RequestBody ClaimPA claimPA) throws IOException, MessagingException {
        claimPAService.reviewClaimPAApproved(id, claimPA);
    }

    @PutMapping("/approvedClaimPA/{id}")
    public void approvedClaimPA(@PathVariable(name = "id") String id, @RequestBody ClaimPA claimPA) throws IOException, MessagingException {
        claimPAService.updateClaimPAAproved(id, claimPA);
    }

    @PutMapping("/rejectedClaimPA/{id}")
    public void rejectedClaimPA(@PathVariable(name = "id") String id, @RequestParam String description) throws IOException, MessagingException {
        claimPAService.updateClaimPARejected(id, description);
    }
}
