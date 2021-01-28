package com.mask.mask.repository;

import com.mask.mask.entity.CustomerTravel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTravelRepository extends JpaRepository<CustomerTravel, String> {
    Page<CustomerTravel> findAll(Specification<CustomerTravel> customerTravelSpecification, Pageable pageable);
    Optional<CustomerTravel> findCustomerTravelByTransactionTravelId(String polisId);
}
