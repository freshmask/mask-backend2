package com.mask.mask.repository;

import com.mask.mask.entity.TransactionPAR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionPARRepository extends JpaRepository <TransactionPAR, String> {
    Page<TransactionPAR> findAll(Specification<TransactionPAR> transactionPARSpecification, Pageable pageable);
    public List<TransactionPAR> findTransactionPARByProductProductId(String productId);
    public List<TransactionPAR> findTransactionPARByIsPromo(String isPromo);
    public TransactionPAR findTransactionPARByTransactionId(String id);
}
