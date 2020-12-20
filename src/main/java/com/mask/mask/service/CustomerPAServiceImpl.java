package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.dto.CustomerPASearchDTO;
import com.mask.mask.entity.CustomerPA;
import com.mask.mask.exception.FileStorageException;
import com.mask.mask.exception.MyFileNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.CustomerPARepository;
import com.mask.mask.specification.CustomerPASpecification;
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
public class CustomerPAServiceImpl implements CustomerPAService {

    @Autowired
    CustomerPARepository customerPARepository;

    @Override
    public CustomerPA addCustomerPA(CustomerPA customerPA) {
        customerPARepository.save(customerPA);
        return customerPA;
    }

    @Override
    public Page<CustomerPA> getCustomerPAInPage(Pageable pageable, CustomerPASearchDTO customerPASearchDTO) {
        Specification<CustomerPA> customerPASpecification= CustomerPASpecification.getSpecification(customerPASearchDTO);
        return customerPARepository.findAll(customerPASpecification, pageable);
    }

    @Override
    public List<CustomerPA> getAllCustomerPA() {
        return customerPARepository.findAll();
    }

    @Override
    public CustomerPA getCustomerPAById(String customerpaId) {
        Optional<CustomerPA> customerPAOptional = customerPARepository.findById(customerpaId);;
        if (customerPAOptional.isPresent()){
            return customerPAOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE, CustomerPA.class,customerpaId));
    }

    @Override
    public void editCustomerPA(String customerpaId, CustomerPA customerPA) {
        CustomerPA newCustomerPA = customerPARepository.findById(customerpaId).get();
        newCustomerPA.setName(customerPA.getName());
        newCustomerPA.setIdentityNo(customerPA.getIdentityNo());
        newCustomerPA.setIdentityType(customerPA.getIdentityType());
        newCustomerPA.setPhoneNumber(customerPA.getPhoneNumber());
        newCustomerPA.setEmail(customerPA.getEmail());
        newCustomerPA.setAddress(customerPA.getAddress());
        newCustomerPA.setHeirName(customerPA.getHeirName());
        newCustomerPA.setRelationship(customerPA.getRelationship());
        newCustomerPA.setHeirPhoneNumber(customerPA.getHeirPhoneNumber());
        newCustomerPA.setHeirEmail(customerPA.getHeirEmail());
        newCustomerPA.setFileNamePhoto(customerPA.getFileNamePhoto());
        newCustomerPA.setFileNameIdentity(customerPA.getFileNameIdentity());
        customerPARepository.save(newCustomerPA);
    }

    @Override
    public void deleteCustomerPAById(String customerpaId) {
        customerPARepository.deleteById(customerpaId);
    }
}
