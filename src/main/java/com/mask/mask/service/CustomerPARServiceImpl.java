package com.mask.mask.service;

import com.mask.mask.FileStorageProperties;
import com.mask.mask.entity.CustomerPAR;
import com.mask.mask.exception.FileStorageException;
import com.mask.mask.exception.LimitOfCompensationException;
import com.mask.mask.exception.MyFileNotFoundException;
import com.mask.mask.repository.CustomerPARRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CustomerPARServiceImpl implements CustomerPARService {

    @Autowired
    CustomerPARRepository customerPARRepository;

    @Override
    public CustomerPAR saveCustomerPAR(CustomerPAR customerPAR) {
        Float coverageValueInitiate = 0.0f;
        coverageValueInitiate = customerPAR.getBuildingCoverageValue() + customerPAR.getMachineCoverageValue() + customerPAR.getFurnitureCoverageValue();
        if (coverageValueInitiate <= 5000000000.0f) {
            return customerPARRepository.save(customerPAR);
        } else {
            throw new LimitOfCompensationException("Nilai Pertanggungan yang anda masukkan lebih dari nilai maksimal pertanggungan sebesar Rp 5.000.000.000,-");
        }
    }

    @Override
    public void updateCustomerPAR(String id, CustomerPAR customerPAR) {
        CustomerPAR customerPAR1 = customerPARRepository.findById(id).get();
        customerPAR1.setName(customerPAR.getName());
        customerPAR1.setIdentityType(customerPAR.getIdentityType());
        customerPAR1.setIdentityNo(customerPAR.getIdentityNo());
        customerPAR1.setPhoneNumber(customerPAR.getPhoneNumber());
        customerPAR1.setEmail(customerPAR.getEmail());
        customerPAR1.setTimePeriod(customerPAR.getTimePeriod());
        customerPAR1.setRiskLocation(customerPAR.getRiskLocation());
        customerPAR1.setOccupation(customerPAR.getOccupation());
        customerPAR1.setConstructionClass(customerPAR.getConstructionClass());
        customerPAR1.setBuildingType(customerPAR.getBuildingType());
        customerPAR1.setBuildingYear(customerPAR.getBuildingYear());
        customerPAR1.setFloorNumber(customerPAR.getFloorNumber());
        customerPAR1.setSprinkle(customerPAR.getSprinkle());
        customerPAR1.setBuildingCoverageValue(customerPAR.getBuildingCoverageValue());
        customerPAR1.setPump(customerPAR.getPump());
        customerPAR1.setFireAlarm(customerPAR.getFireAlarm());
        customerPAR1.setDocumentName(customerPAR.getDocumentName());
        customerPARRepository.save(customerPAR1);

    }

    @Override
    public CustomerPAR getCustomerPARById(String id) {
        return customerPARRepository.findById(id).get();
    }

    @Override
    public Page<CustomerPAR> getCustomerPARPerPage(Pageable pageable) {
        return customerPARRepository.findAll(pageable);
    }

    @Override
    public void deleteCustomerPARById(String id) {
        customerPARRepository.deleteById(id);
    }

    @Override
    public CustomerPAR getCustomerPARByTrxparId(String id) {
        return customerPARRepository.findCustomerPARByTransactionPARTrxparId(id);
    }

}
