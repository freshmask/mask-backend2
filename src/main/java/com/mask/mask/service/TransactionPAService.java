package com.mask.mask.service;

import com.mask.mask.dto.TransactionPASearchDTO;
import com.mask.mask.entity.TransactionPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionPAService {
    public void addTransactionPA(TransactionPA transactionPA);
    public Page<TransactionPA> getTransactionPAInPage(Pageable pageable, TransactionPASearchDTO transactionPASearchDTO);
    public List<TransactionPA> getAllTransactionPA();
    public TransactionPA getTransactionPAById (String trxpaId);
    public void updateTransactionPA(TransactionPA transactionPA);
    public void deleteTransactionPAById (String trxpaId);
    public List<TransactionPA> getTransactionPAByCategoryPAId(String categoryId);
    public List<TransactionPA> findTransactionPAByIsPromo(String isPromo);

    public TransactionPA getransactionPAByTransactionId (String id);
    public List<TransactionPA> getTransactionPAByUserId(String userID);

}
