package com.mask.mask.service;

import com.mask.mask.dto.TransactionPARSearchDTO;
import com.mask.mask.entity.*;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.TransactionPARRepository;
import com.mask.mask.specification.TransactionPARSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionPARServiceImpl implements TransactionPARService {

    @Autowired
    TransactionPARRepository transactionPARRepository;

    @Autowired
    TransactionService transactionService;

    @Override
    public void addTransactionPAR(TransactionPAR transactionPAR) {
        TransactionPAR transactionPAR1 = transactionPARRepository.save(transactionPAR);
        CustomerPAR customerPAR = transactionPAR.getCustomerPAR();
        customerPAR.setTransactionPAR(transactionPAR1);
        transactionPARRepository.save(transactionPAR);
    }

    @Override
    public Page<TransactionPAR> getTransactionPARInPage(Pageable pageable, TransactionPARSearchDTO transactionPARSearchDTO) {
        Specification<TransactionPAR> transactionPARSpecification= TransactionPARSpecification.getSpecification(transactionPARSearchDTO);
        return transactionPARRepository.findAll(transactionPARSpecification, pageable);
    }

    @Override
    public List<TransactionPAR> getAllTransactionPAR() {
        return transactionPARRepository.findAll();
    }

    @Override
    public TransactionPAR getTransactionPARById(String trxparId) {
        Optional<TransactionPAR> transactionPAROptional = transactionPARRepository.findById(trxparId);;
        if (transactionPAROptional.isPresent()){
            return transactionPAROptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,TransactionPAR.class,trxparId));
    }

    @Override
    public void updateTransactionPAR(TransactionPAR transactionPAR) {
        transactionPARRepository.save(transactionPAR);
    }

    @Override
    public void deleteTransactionPARById(String trxparId) {
        transactionPARRepository.deleteById(trxparId);
    }

    @Override
    public List<TransactionPAR> getTransactionPARByProductProductId(String productId) {
        return transactionPARRepository.findTransactionPARByProductProductId(productId);
    }

    @Override
    public List<TransactionPAR> findTransactionPARByIsPromo(String isPromo) {
        return transactionPARRepository.findTransactionPARByIsPromo(isPromo);
    }

    @Override
    public TransactionPAR getTransactionPARByTransactionId(String id) {
        return transactionPARRepository.findTransactionPARByTransactionId(id);
    }

    @Override
    public List<TransactionPAR> getTransactionPARByUserId(String userID) {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userID);
        List<TransactionPAR> transactionPARList = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            TransactionPAR transactionPAR = transactionPARRepository.findTransactionPARByTransactionId(transaction.getId());
            if(transactionPAR != null){
                transactionPARList.add(transactionPAR);
            }
        }
        return transactionPARList;
    }
}
