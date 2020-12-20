package com.mask.mask.repository;

import com.mask.mask.entity.CustomerPAR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPARRepository extends JpaRepository<CustomerPAR, String> {
    public CustomerPAR findCustomerPARByTransactionPARTrxparId(String id);
}
