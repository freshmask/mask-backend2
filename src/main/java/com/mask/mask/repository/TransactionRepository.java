package com.mask.mask.repository;

import com.mask.mask.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public List<Transaction> findTransactionByUsersId(String id);
    public List<Transaction> findTransactionByTransactionPATrxpaId(String id);
    public List<Transaction> findTransactionByTransactionTravelId(String id);
    
}
