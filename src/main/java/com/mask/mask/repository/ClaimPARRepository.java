package com.mask.mask.repository;

import com.mask.mask.entity.ClaimPAR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimPARRepository extends JpaRepository<ClaimPAR, String> {
    Page<ClaimPAR> findAll(Specification<ClaimPAR> claimPARSpecification, Pageable pageable);
}
