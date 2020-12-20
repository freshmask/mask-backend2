package com.mask.mask.service;

import com.mask.mask.dto.TransactionPARSearchDTO;
import com.mask.mask.entity.TransactionPA;
import com.mask.mask.entity.TransactionPAR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionPARService {
    public void addTransactionPAR(TransactionPAR transactionPAR);
    public Page<TransactionPAR> getTransactionPARInPage(Pageable pageable, TransactionPARSearchDTO transactionPARSearchDTO);
    public List<TransactionPAR> getAllTransactionPAR();
    public TransactionPAR getTransactionPARById (String trxparId);
    public void updateTransactionPAR(TransactionPAR transactionPAR);
    public void deleteTransactionPARById (String trxparId);
    public List<TransactionPAR> getTransactionPARByProductProductId(String productId);
    public List<TransactionPAR> findTransactionPARByIsPromo(String isPromo);

    public TransactionPAR getTransactionPARByTransactionId (String id);
    public List<TransactionPAR> getTransactionPARByUserId(String userID);
}
