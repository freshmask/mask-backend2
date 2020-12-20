package com.mask.mask.controller;

import com.mask.mask.entity.CustomerPAR;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.CustomerPARService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
public class CustomerPARController {

    @Value("${document-customer}")
    String documentCustomer;

    @Autowired
    CustomerPARService customerPARService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerPARController.class);


    @PostMapping("/customerpar")
    public CustomerPAR saveCustomerPAR(@RequestPart (required = false) MultipartFile document,
                                       @RequestParam String name,
                                       @RequestParam String identityType,
                                       @RequestParam String identityNo,
                                       @RequestParam String phoneNumber,
                                       @RequestParam String email,
                                       @RequestParam Integer timePeriod,
                                       @RequestParam String riskLocation,
                                       @RequestParam String occupation,
                                       @RequestParam String constructionClass,
                                       @RequestParam String buildingType,
                                       @RequestParam String machineType,
                                       @RequestParam String furnitureType,
                                       @RequestParam String buildingYear,
                                       @RequestParam String floorNumber,
                                       @RequestParam String sprinkle,
                                       @RequestParam Float buildingCoverageValue,
                                       @RequestParam Float machineCoverageValue,
                                       @RequestParam Float furnitureCoverageValue,
                                       @RequestParam String pump,
                                       @RequestParam String fireAlarm,
                                       @RequestParam String address) {
        try{
            if ((document != null)) {
            document.transferTo(Paths.get(documentCustomer, "PAR-Document-" + name + "."+ FilenameUtils.getExtension(document.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String documentName = StringUtils.cleanPath("PAR-Document-" + name + "."+ FilenameUtils.getExtension(document.getOriginalFilename()));
        CustomerPAR customerPAR = new CustomerPAR(name, identityType, identityNo, phoneNumber, email, timePeriod, riskLocation, occupation, constructionClass, buildingYear,floorNumber, sprinkle, buildingType, buildingCoverageValue, machineType, machineCoverageValue, furnitureType, furnitureCoverageValue, pump, fireAlarm, address, documentName);
        return customerPARService.saveCustomerPAR(customerPAR);
    }


    @DeleteMapping("/customerpar")
    public void deleteCustomerPARById(@RequestParam(name = "id") String id) {
        customerPARService.deleteCustomerPARById(id);
    }

    @PutMapping("/customerpar/{id}")
    public void updateCustomerPAR(@PathVariable String id,
                                  @RequestPart (required = false) MultipartFile document,
                                  @RequestParam String name,
                                  @RequestParam String identityType,
                                  @RequestParam String identityNo,
                                  @RequestParam String phoneNumber,
                                  @RequestParam String email,
                                  @RequestParam Integer timePeriod,
                                  @RequestParam String riskLocation,
                                  @RequestParam String occupation,
                                  @RequestParam String constructionClass,
                                  @RequestParam String buildingType,
                                  @RequestParam String machineType,
                                  @RequestParam String furnitureType,
                                  @RequestParam String buildingYear,
                                  @RequestParam String floorNumber,
                                  @RequestParam String sprinkle,
                                  @RequestParam Float buildingCoverageValue,
                                  @RequestParam Float machineCoverageValue,
                                  @RequestParam Float furnitureCoverageValue,
                                  @RequestParam String pump,
                                  @RequestParam String fireAlarm,
                                  @RequestParam String address){
        try{
            if ((document != null)) {
            document.transferTo(Paths.get(documentCustomer, "PAR-Document-" + name + "."+ FilenameUtils.getExtension(document.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String documentName = StringUtils.cleanPath("PAR-Document-" + name + "."+ FilenameUtils.getExtension(document.getOriginalFilename()));
        CustomerPAR customerPAR = new CustomerPAR(name, identityType, identityNo, phoneNumber, email, timePeriod, riskLocation, occupation, constructionClass, buildingYear,floorNumber, sprinkle, buildingType, buildingCoverageValue, machineType, machineCoverageValue, furnitureType, furnitureCoverageValue, pump, fireAlarm, address, documentName);
        customerPARService.updateCustomerPAR(id, customerPAR);
    }

    @GetMapping("/customerpars")
    public Page<CustomerPAR> getAllCustomerPARPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerPARService.getCustomerPARPerPage(pageable);
    }

    @GetMapping("/customerpar/{id}")
    public CustomerPAR getCustomerPARbyId(@PathVariable(name = "id") String id) {
        return customerPARService.getCustomerPARById(id);
    }

}
