package com.mask.mask.controller;

import com.mask.mask.dto.CustomerTravelSearchDTO;
import com.mask.mask.entity.CustomerTravel;
import com.mask.mask.exception.IncompleteFileException;
import com.mask.mask.service.CustomerTravelService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerTravelController {

    @Value("${document-customer}")
    String documentCustomer;

    @Autowired
    CustomerTravelService customerTravelService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerTravelController.class);

    @PostMapping("/customerTravel")
    public CustomerTravel addCustomerTravel(@RequestPart (required = false) MultipartFile filePhoto,
                                        @RequestPart (required = false) MultipartFile fileIdentity,
                                        @RequestParam String name,
                                        @RequestParam String address,
                                        @RequestParam String identityType,
                                        @RequestParam String identityNo,
                                        @RequestParam String phoneNumber,
                                        @RequestParam String email,
                                        @RequestParam String heirName,
                                        @RequestParam String heirPhoneNumber,
                                        @RequestParam String relationship,
                                        @RequestParam String heirEmail,
                                        @RequestParam String travelKind,
                                        @RequestParam String departureCity,
                                        @RequestParam String destinationCity,
                                        @RequestParam Date departureDate,
                                        @RequestParam String journeyLength,
                                        @RequestParam String departurePurpose,
                                        @RequestParam String voucherTravel) {
        try{
            if ((filePhoto != null) && (fileIdentity != null)) {
            filePhoto.transferTo(Paths.get(documentCustomer, "TRV-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename())));
            fileIdentity.transferTo(Paths.get(documentCustomer, "TRV-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePhotoName = StringUtils.cleanPath("TRV-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename()));
        String fileIdentityName = StringUtils.cleanPath("TRV-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename()));
        CustomerTravel customerTravel = new CustomerTravel(name, address, identityType, identityNo, phoneNumber, email, heirName, heirPhoneNumber, relationship, heirEmail, travelKind, departureCity, destinationCity, departureDate, journeyLength, departurePurpose, filePhotoName, fileIdentityName,voucherTravel);
        return customerTravelService.addCustomerTravel(customerTravel);
    }

    @GetMapping("/customersTravel")
    public Page<CustomerTravel> searchCustomerTravel (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                      @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                      @RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "address", required = false) String address,
                                                      @RequestParam(name = "identityType", required = false) String identityType,
                                                      @RequestParam(name = "identityNo", required = false) String identityNo,
                                                      @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                                      @RequestParam(name = "email", required = false) String email,
                                                      @RequestParam(name = "heirName", required = false) String heirName,
                                                      @RequestParam(name = "heirPhoneNumber", required = false) String heirPhoneNumber,
                                                      @RequestParam(name = "relationship", required = false) String relationship,
                                                      @RequestParam(name = "heirEmail", required = false) String heirEmail,
                                                      @RequestParam(name = "travelKind", required = false) String travelKind,
                                                      @RequestParam(name = "departureCity", required = false) String departureCity,
                                                      @RequestParam(name = "destinationCity", required = false) String destinationCity,
                                                      @RequestParam(name = "journeyLength", required = false) String journeyLength,
                                                      @RequestParam(name = "departurePurpose", required = false) String departurePurpose){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        CustomerTravelSearchDTO customerTravelSearchDTO = new CustomerTravelSearchDTO(name, address, identityType, identityNo, phoneNumber, email, heirName, heirPhoneNumber, relationship, heirEmail, travelKind, departureCity, destinationCity, journeyLength,departurePurpose);
        return customerTravelService.getCustomerTravelInPage(pageable, customerTravelSearchDTO);
    }

    @GetMapping("/customersTravel/list")
    public List<CustomerTravel> getAllListCustomerTravel (){
        return customerTravelService.getAllCustomerTravel();
    }

    @GetMapping("/customerTravel/{customertravelId}")
    public CustomerTravel getCustomerTravelById(@PathVariable(name = "customertravelId") String customertravelId){
        CustomerTravel customerTravel = customerTravelService.getCustomerTravelById(customertravelId);
        return customerTravel;
    }

    @PutMapping("/customersTravel/{customertravelId}")
    public void editCustomerTravel(@RequestParam String customertravelId,
                               @RequestPart (required = false) MultipartFile filePhoto,
                               @RequestPart (required = false) MultipartFile fileIdentity,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam String identityType,
                               @RequestParam String identityNo,
                               @RequestParam String phoneNumber,
                               @RequestParam String email,
                               @RequestParam String heirName,
                               @RequestParam String heirPhoneNumber,
                               @RequestParam String relationship,
                               @RequestParam String heirEmail,
                               @RequestParam String travelKind,
                               @RequestParam String departureCity,
                               @RequestParam String destinationCity,
                               @RequestParam Date departureDate,
                               @RequestParam String journeyLength,
                               @RequestParam String departurePurpose,
                               @RequestParam String voucherTravel) {
        try{
            if ((filePhoto != null) && (fileIdentity != null)) {
            filePhoto.transferTo(Paths.get(documentCustomer, "TRV-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename())));
            fileIdentity.transferTo(Paths.get(documentCustomer, "TRV-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename())));
            } else {
                throw new IncompleteFileException("Dokumen tidak lengkap");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePhotoName = StringUtils.cleanPath("TRV-Swafoto-" + name + "."+ FilenameUtils.getExtension(filePhoto.getOriginalFilename()));
        String fileIdentityName = StringUtils.cleanPath("TRV-KTP-"+name +"."+ FilenameUtils.getExtension(fileIdentity.getOriginalFilename()));
        CustomerTravel customerTravel = new CustomerTravel(name, address, identityType, identityNo, phoneNumber, email, heirName, heirPhoneNumber, relationship, heirEmail, travelKind, departureCity, destinationCity, departureDate, journeyLength, departurePurpose, filePhotoName, fileIdentityName,voucherTravel);
        customerTravelService.editCustomerTravel(customertravelId, customerTravel);
    }

    @DeleteMapping("/customerTravel")
    public void deleteCustomerTravelById (@RequestParam(name = "customertravelId") String customertravelId){
        customerTravelService.deleteCustomerTravelById(customertravelId);
    }

    @GetMapping("/customerTravelByPolis/{polisId}")
    public CustomerTravel getCustomerTravelByPolisId(@PathVariable(name = "polisId") String polisId){
        CustomerTravel customerTravel = customerTravelService.getCustomerTravelByPolisId(polisId);
        return customerTravel;
    }
}
