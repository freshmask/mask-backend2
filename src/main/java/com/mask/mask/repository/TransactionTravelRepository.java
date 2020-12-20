package com.mask.mask.repository;

import com.mask.mask.entity.TransactionTravel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionTravelRepository extends JpaRepository<TransactionTravel, String> {
    public List<TransactionTravel> findTransactionTravelByPackageTravelPtId(String ptId);
    public List<TransactionTravel> findTransactionTravelByIsPromo(String isPromo);
    public TransactionTravel findTransactionTravelByTransactionId(String id);
    public List<TransactionTravel> findAllByStatusPolis(String status);
}
