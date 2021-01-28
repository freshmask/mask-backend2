package com.mask.mask.controller;

import com.mask.mask.dto.CustomerPASearchDTO;
import com.mask.mask.entity.CustomerPA;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.CustomerPAService;
import com.mask.mask.service.CustomerPAServiceImpl;
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
import java.util.List;

@RestController
public class CustomerPAController {

    @Value("${document-customer}")
    String documentCustomer;

    @Autowired
    CustomerPAService customerPAService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerPAController.class);

    @PostMapping("/customerPA")
    public CustomerPA addCustomerPA(@RequestPart (required = false) MultipartFile filePhoto,
                              @RequestPart (required = false) MultipartFile fileIdentity,
                              @RequestParam String name,
                              @RequestParam String identityNo,
                              @RequestParam String identityType,
                              @RequestParam String phoneNumber,
                              @RequestParam String email,
                              @RequestParam String address,
                              @RequestParam String heirName,
                              @RequestParam String relationship,
                              @RequestParam String heirPhoneNumber,
                              @RequestParam String heirEmail,
                                    @RequestParam String voucherPA) {

        try{
            if ((filePhoto != null) && (fileIdentity != null)) {
            filePhoto.transferTo(Paths.get(documentCustomer, "PA-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename())));
            fileIdentity.transferTo(Paths.get(documentCustomer, "PA-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = StringUtils.cleanPath("PA-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename()));
        String fileIden = StringUtils.cleanPath("PA-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename()));
        CustomerPA customerPA = new CustomerPA(name, identityNo, identityType, phoneNumber, email, address, heirName, relationship, heirPhoneNumber, heirEmail, filename, fileIden, voucherPA);
        return customerPAService.addCustomerPA(customerPA);
    }

    @GetMapping("/customersPA")
    public Page<CustomerPA> searchCustomerPA (@RequestParam(name="page", defaultValue = "0") Integer page,
                                              @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                              @RequestParam(name = "name", required = false) String name,
                                              @RequestParam(name = "identityNo", required = false) String identityNo,
                                              @RequestParam(name = "identityType", required = false) String identityType,
                                              @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                              @RequestParam(name = "email", required = false) String email,
                                              @RequestParam(name = "address", required = false) String address,
                                              @RequestParam(name = "heirName", required = false) String heirName,
                                              @RequestParam(name = "relationship", required = false) String relationship,
                                              @RequestParam(name = "heirPhoneNumber", required = false) String heirPhoneNumber,
                                              @RequestParam(name = "heirEmail", required = false) String heirEmail){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        CustomerPASearchDTO customerPASearchDTO = new CustomerPASearchDTO(name, identityNo, identityType, phoneNumber, email, address, heirName, relationship, heirPhoneNumber, heirEmail);
        return customerPAService.getCustomerPAInPage(pageable, customerPASearchDTO);
    }

    @GetMapping("/customerPA/list")
    public List<CustomerPA> getAllListCustomerPA (){
        return customerPAService.getAllCustomerPA();
    }

    @GetMapping("/customerPA/{customerpaId}")
    public CustomerPA getCustomerPAById(@PathVariable(name = "customerpaId") String customerpaId){
        CustomerPA customerPA = customerPAService.getCustomerPAById(customerpaId);
        return customerPA;
    }

    @PutMapping("/updateCustomerPA/{customerpaId}")
    public void editCustomerPA(@PathVariable String customerpaId,
                               @RequestPart MultipartFile filePhoto,
                               @RequestPart MultipartFile fileIdentity,
                               @RequestParam String name,
                               @RequestParam String identityNo,
                               @RequestParam String identityType,
                               @RequestParam String phoneNumber,
                               @RequestParam String email,
                               @RequestParam String address,
                               @RequestParam String heirName,
                               @RequestParam String relationship,
                               @RequestParam String heirPhoneNumber,
                               @RequestParam String heirEmail,
                               @RequestParam String voucherPA) {
        try{
            if ((filePhoto != null) && (fileIdentity != null)) {
            filePhoto.transferTo(Paths.get(documentCustomer, "PA-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename())));
            fileIdentity.transferTo(Paths.get(documentCustomer, "PA-KTP-"+name +"."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = StringUtils.cleanPath("PA-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename()));
        String fileIden = StringUtils.cleanPath("PA-KTP-"+name +"."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename()));
        CustomerPA customerPA = new CustomerPA(name, identityNo, identityType, phoneNumber, email, address, heirName, relationship, heirPhoneNumber, heirEmail, filename, fileIden, voucherPA);
        customerPAService.editCustomerPA(customerpaId, customerPA);
    }

    @DeleteMapping("/customerPA")
    public void deleteCustomerPAById (@RequestParam(name = "customerpaId") String customerpaId){
        customerPAService.deleteCustomerPAById(customerpaId);
    }

    @GetMapping("/customerPAByPolis/{polisId}")
    public CustomerPA getCustomerPAByPolisId(@PathVariable(name = "polisId") String polisId){
        CustomerPA customerPA = customerPAService.getCustomerPAByPolisId(polisId);
        return customerPA;
    }
}
