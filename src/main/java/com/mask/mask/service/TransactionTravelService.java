package com.mask.mask.service;

import com.mask.mask.entity.TransactionPA;
import com.mask.mask.entity.TransactionTravel;
import com.mask.mask.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionTravelService {
    public void saveTransactionTravel(TransactionTravel transactionTravel);
    public void updateTransactionTravel(String id, TransactionTravel transactionTravel);
    public void deleteTransactionTravelById(String id);
    public Page<TransactionTravel> getAllTransactionTravelPerPage(Pageable pageable);
    public List<TransactionTravel> getAllTransactionTravel();
    public TransactionTravel getTransactionTravelById(String id);
    public List<TransactionTravel> findTransactionTravelByPackageTravelPtId(String ptId);
    public List<TransactionTravel> findTransactionTravelByIsPromo(String isPromo);
    public TransactionTravel getransactionTravelByTransactionId (String id);
    public List<TransactionTravel> getTransactionTravelByUserId(String userID);
}
