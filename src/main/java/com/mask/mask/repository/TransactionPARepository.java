package com.mask.mask.repository;

import com.mask.mask.entity.PackagePA;
import com.mask.mask.entity.TransactionPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionPARepository extends JpaRepository <TransactionPA, String> {
    Page<TransactionPA> findAll(Specification<TransactionPA> transactionPASpecification, Pageable pageable);
    public List<TransactionPA> findTransactionPAByCategoryPACategoryId(String id);
    public List<TransactionPA> findTransactionPAByIsPromo(String isPromo);
    public TransactionPA findTransactionPAByTransactionId(String transactionId);
}
