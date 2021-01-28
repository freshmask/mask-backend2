package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.dto.CustomerTravelSearchDTO;
import com.mask.mask.entity.CustomerPA;
import com.mask.mask.entity.CustomerTravel;
import com.mask.mask.exception.FileStorageException;
import com.mask.mask.exception.MyFileNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.CustomerTravelRepository;
import com.mask.mask.specification.CustomerPASpecification;
import com.mask.mask.specification.CustomerTravelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerTravelServiceImpl implements CustomerTravelService {

    @Autowired
    CustomerTravelRepository customerTravelRepository;

    @Override
    public CustomerTravel addCustomerTravel(CustomerTravel customerTravel) {
        customerTravelRepository.save(customerTravel);
        return customerTravel;
    }

    @Override
    public Page<CustomerTravel> getCustomerTravelInPage(Pageable pageable, CustomerTravelSearchDTO customerTravelSearchDTO) {
        Specification<CustomerTravel> customerTravelSpecification= CustomerTravelSpecification.getSpecification(customerTravelSearchDTO);
        return customerTravelRepository.findAll(customerTravelSpecification, pageable);
    }

    @Override
    public List<CustomerTravel> getAllCustomerTravel() {
        return customerTravelRepository.findAll();
    }

    @Override
    public CustomerTravel getCustomerTravelById(String customertravelId) {
        Optional<CustomerTravel> customerTravelOptional = customerTravelRepository.findById(customertravelId);;
        if (customerTravelOptional.isPresent()){
            return customerTravelOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE, CustomerTravel.class,customertravelId));
    }

    @Override
    public void editCustomerTravel(String customertravelId, CustomerTravel customerTravel) {
        CustomerTravel newCustomerTravel = customerTravelRepository.findById(customertravelId).get();
        newCustomerTravel.setName(customerTravel.getName());
        newCustomerTravel.setAddress(customerTravel.getAddress());
        newCustomerTravel.setIdentityType(customerTravel.getIdentityType());
        newCustomerTravel.setIdentityNo(customerTravel.getIdentityNo());
        newCustomerTravel.setPhoneNumber(customerTravel.getPhoneNumber());
        newCustomerTravel.setEmail(customerTravel.getEmail());
        newCustomerTravel.setHeirName(customerTravel.getHeirName());
        newCustomerTravel.setHeirPhoneNumber(customerTravel.getHeirPhoneNumber());
        newCustomerTravel.setRelationship(customerTravel.getRelationship());
        newCustomerTravel.setHeirEmail(customerTravel.getHeirEmail());
        newCustomerTravel.setTravelKind(customerTravel.getTravelKind());
        newCustomerTravel.setDepartureCity(customerTravel.getDepartureCity());
        newCustomerTravel.setDestinationCity(customerTravel.getDestinationCity());
        newCustomerTravel.setDepartureDate(customerTravel.getDepartureDate());
        newCustomerTravel.setJourneyLength(customerTravel.getJourneyLength());
        newCustomerTravel.setDeparturePurpose(customerTravel.getDeparturePurpose());
        newCustomerTravel.setFileNamePhoto(customerTravel.getFileNamePhoto());
        newCustomerTravel.setFileNameIdentity(customerTravel.getFileNameIdentity());
        customerTravelRepository.save(newCustomerTravel);
    }

    @Override
    public void deleteCustomerTravelById(String customertravelId) {
        customerTravelRepository.deleteById(customertravelId);
    }

    @Override
    public CustomerTravel getCustomerTravelByPolisId(String polisId) {
        Optional<CustomerTravel> customerTravelOptional = customerTravelRepository.findCustomerTravelByTransactionTravelId(polisId);
        if (customerTravelOptional.isPresent()){
            return customerTravelOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE, CustomerTravel.class,polisId));
    }
}
