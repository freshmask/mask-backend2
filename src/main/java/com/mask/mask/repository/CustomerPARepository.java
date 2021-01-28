package com.mask.mask.repository;

import com.mask.mask.entity.CustomerPA;
import com.mask.mask.entity.CustomerTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerPARepository extends JpaRepository<CustomerPA, String> {
    Page<CustomerPA> findAll(Specification<CustomerPA> customerPASpecification, Pageable pageable);
    Optional<CustomerPA> findCustomerPAByTransactionPATrxpaId(String polisId);
}
