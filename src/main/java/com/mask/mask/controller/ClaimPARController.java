package com.mask.mask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mask.mask.dto.ClaimPARSearchDTO;
import com.mask.mask.entity.ClaimPAR;
import com.mask.mask.entity.Transaction;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.ClaimPARService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
public class ClaimPARController {


    @Value("${document-claim}")
    String documentClaim;

    @Autowired
    ClaimPARService claimPARService;

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClaimPARController.class);

    @PostMapping("/claimPAR")
    public String addClaimPAR(@RequestPart (required = false) MultipartFile lossReport,
                                @RequestPart (required = false) MultipartFile authoritiesReport,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam Date reportDate,
                                @RequestParam Date incidentDate,
                                @RequestParam String lossCause,
                                @RequestParam String incidentLocation,
                                @RequestParam String machineType,
                                @RequestParam String buildingType,
                                @RequestParam String furnitureType,
                                @RequestParam Float claimSubmission,
                                @RequestParam Float claimApproval,
                                @RequestParam String transaction) throws JsonProcessingException {
        try{
            if ((lossReport != null) && (authoritiesReport != null)) {
                lossReport.transferTo(Paths.get(documentClaim, "PAR-LR-" + name + "."+ FilenameUtils.getExtension(lossReport.getOriginalFilename())));
                authoritiesReport.transferTo(Paths.get(documentClaim, "PAR-AR-"+name +"."+ FilenameUtils.getExtension(authoritiesReport.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lossReportName = StringUtils.cleanPath("PAR-LR-" + name + "."+ FilenameUtils.getExtension(lossReport.getOriginalFilename()));
        String authoritiesReportName = StringUtils.cleanPath("PAR-AR-"+name +"."+ FilenameUtils.getExtension(authoritiesReport.getOriginalFilename()));
        Transaction transaction1 = objectMapper.readValue(transaction, Transaction.class);
        ClaimPAR claimPAR = new ClaimPAR(name, email, reportDate, incidentDate, lossCause, incidentLocation, furnitureType, buildingType, machineType, claimSubmission, claimApproval, lossReportName, authoritiesReportName, transaction1);
        claimPARService.addClaimPAR(claimPAR);
        return "Sukses";
    }

    @GetMapping("/claimsPAR")
    public Page<ClaimPAR> searchClaimPAR (@RequestParam(name="page", defaultValue = "0") Integer page,
                                          @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                          @RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "email", required = false) String email,
                                          @RequestParam(name = "lossCause", required = false) String lossCause,
                                          @RequestParam(name = "incidentLocation", required = false) String incidentLocation,
                                          @RequestParam(name = "productType", required = false) String productType){
        Pageable pageable= PageRequest.of(page,sizePerPage, Sort.by(Sort.Direction.DESC, "creationDate"));
        ClaimPARSearchDTO claimPARSearchDTO = new ClaimPARSearchDTO(name, email, lossCause, incidentLocation, productType);
        return claimPARService.getClaimPARInPage(pageable, claimPARSearchDTO);
    }

    @GetMapping("/claimPAR/list")
    public List<ClaimPAR> getAllListClaimPAR (){
        return claimPARService.getAllClaimPAR();
    }

    @GetMapping("/claimPAR/{claimparId}")
    public ClaimPAR getClaimPARById(@PathVariable(name = "claimparId") String claimparId){
        ClaimPAR claimPAR = claimPARService.getClaimPARById(claimparId);
        return claimPAR;
    }

    @PutMapping("/updateClaimPAR/{claimparId}")
    public void editClaimPAR(@PathVariable String claimparId,
                             @RequestPart MultipartFile lossReport,
                             @RequestPart MultipartFile authoritiesReport,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam Date reportDate,
                             @RequestParam Date incidentDate,
                             @RequestParam String lossCause,
                             @RequestParam String incidentLocation,
                             @RequestParam String machineType,
                             @RequestParam String buildingType,
                             @RequestParam String furnitureType,
                             @RequestParam Float claimSubmission,
                             @RequestParam Float claimApproval) {
        try{
            lossReport.transferTo(Paths.get(documentClaim, "PAR-LR-" + name + "."+ FilenameUtils.getExtension(lossReport.getOriginalFilename())));
            authoritiesReport.transferTo(Paths.get(documentClaim, "PAR-AR-"+name +"."+ FilenameUtils.getExtension(authoritiesReport.getOriginalFilename())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lossReportName = StringUtils.cleanPath("PAR-LR-" + name + "."+ FilenameUtils.getExtension(lossReport.getOriginalFilename()));
        String authoritiesReportName = StringUtils.cleanPath("PAR-AR-"+name +"."+ FilenameUtils.getExtension(authoritiesReport.getOriginalFilename()));
        ClaimPAR claimPAR = new ClaimPAR(name, email, reportDate, incidentDate, lossCause, incidentLocation, furnitureType, buildingType, machineType, claimSubmission, claimApproval, lossReportName, authoritiesReportName);
        claimPARService.editClaimPAR(claimparId, claimPAR);
    }


    @DeleteMapping("/claimPAR")
    public void deleteClaimPARById (@RequestParam(name = "claimparId") String claimparId){
        claimPARService.deleteClaimPARById(claimparId);
    }

    @PutMapping("/reviewPAR/{id}")
    public void reviewApprovedPAR (@PathVariable String id, @RequestBody ClaimPAR claimPAR) throws IOException, MessagingException {
        claimPARService.reviewClaimPARApproved(id, claimPAR);
    }

    @PutMapping("/approvedClaimPAR/{id}")
    public void updateApprovedPAR (@PathVariable String id, @RequestBody ClaimPAR claimPAR) throws IOException, MessagingException {
        claimPARService.updateClaimPARApproved(id, claimPAR);
    }


    @PutMapping("/rejectedClaimPAR/{id}")
    public void updateRejectedPAR (@PathVariable String id, @RequestParam String description) throws IOException, MessagingException {
        claimPARService.updateClaimPARRejected(id, description);}
}
