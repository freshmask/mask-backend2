package com.mask.mask.service;

import com.mask.mask.entity.CustomerPAR;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerPARService {

    public CustomerPAR saveCustomerPAR(CustomerPAR customerPAR);
    public void updateCustomerPAR(String id,CustomerPAR customerPAR);
    public CustomerPAR getCustomerPARById(String id);
    public Page<CustomerPAR> getCustomerPARPerPage(Pageable pageable);
    public void deleteCustomerPARById(String id);

    public CustomerPAR getCustomerPARByTrxparId(String id);
}
