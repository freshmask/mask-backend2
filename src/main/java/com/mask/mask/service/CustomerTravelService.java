package com.mask.mask.service;

import com.mask.mask.dto.CustomerTravelSearchDTO;
import com.mask.mask.entity.CustomerTravel;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerTravelService {
    public CustomerTravel addCustomerTravel(CustomerTravel customerTravel);
    public Page<CustomerTravel> getCustomerTravelInPage(Pageable pageable, CustomerTravelSearchDTO customerTravelSearchDTO);
    public List<CustomerTravel> getAllCustomerTravel();
    public CustomerTravel getCustomerTravelById (String customertravelId);
    public void editCustomerTravel(String customertravelId, CustomerTravel customerTravel);
    public void deleteCustomerTravelById (String customertravelId);
    public CustomerTravel getCustomerTravelByPolisId (String polisId);
}
