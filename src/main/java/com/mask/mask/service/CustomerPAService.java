package com.mask.mask.service;

import com.mask.mask.dto.CustomerPASearchDTO;
import com.mask.mask.entity.CustomerPA;
import com.mask.mask.entity.CustomerTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerPAService {
    public CustomerPA addCustomerPA(CustomerPA customerPA);
    public Page<CustomerPA> getCustomerPAInPage(Pageable pageable, CustomerPASearchDTO customerPASearchDTO);
    public List<CustomerPA> getAllCustomerPA();
    public CustomerPA getCustomerPAById (String customerpaId);
    public void editCustomerPA(String customerpaId, CustomerPA customerPA);
    public void deleteCustomerPAById (String customerpaId);
    public CustomerPA getCustomerPAByPolisId (String polisId);
}
