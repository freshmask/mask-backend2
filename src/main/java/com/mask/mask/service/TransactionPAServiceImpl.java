package com.mask.mask.service;

import com.mask.mask.dto.TransactionPASearchDTO;
import com.mask.mask.entity.*;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.TransactionPARepository;
import com.mask.mask.specification.TransactionPASpecification;
import org.bouncycastle.util.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionPAServiceImpl implements TransactionPAService{

    @Autowired
    TransactionPARepository transactionPARepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryPAService categoryPAService;

    @Autowired
    PackagePAService packagePAService;

    @Autowired
    TransactionService transactionService;

    @Override
    public void addTransactionPA(TransactionPA transactionPA) {
        TransactionPA transactionPA1 = transactionPARepository.save(transactionPA);
        CustomerPA customerPA = transactionPA.getCustomerPA();
        customerPA.setTransactionPA(transactionPA1);
        transactionPARepository.save(transactionPA);
    }

    @Override
    public Page<TransactionPA> getTransactionPAInPage(Pageable pageable, TransactionPASearchDTO transactionPASearchDTO) {
        Specification<TransactionPA> transactionPASpecification= TransactionPASpecification.getSpecification(transactionPASearchDTO);
        return transactionPARepository.findAll(transactionPASpecification, pageable);
    }

    @Override
    public List<TransactionPA> getAllTransactionPA() {
        return transactionPARepository.findAll();
    }

    @Override
    public TransactionPA getTransactionPAById(String trxpaId) {
        Optional<TransactionPA> transactionPAOptional = transactionPARepository.findById(trxpaId);;
        if (transactionPAOptional.isPresent()){
            return transactionPAOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,TransactionPA.class,trxpaId));
    }

    @Override
    public void updateTransactionPA(TransactionPA transactionPA) {
        transactionPARepository.save(transactionPA);
    }

    @Override
    public void deleteTransactionPAById(String trxpaId) {
        transactionPARepository.deleteById(trxpaId);
    }

    @Override
    public List<TransactionPA> getTransactionPAByCategoryPAId(String categoryId) {
        return transactionPARepository.findTransactionPAByCategoryPACategoryId(categoryId);
    }

    @Override
    public List<TransactionPA> findTransactionPAByIsPromo(String isPromo) {
        return transactionPARepository.findTransactionPAByIsPromo(isPromo);
    }

    @Override
    public TransactionPA getransactionPAByTransactionId(String id) {
        return transactionPARepository.findTransactionPAByTransactionId(id);
    }

    @Override
    public List<TransactionPA> getTransactionPAByUserId(String userID) {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userID);
        List<TransactionPA> transactionPAList = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            TransactionPA transactionPA = transactionPARepository.findTransactionPAByTransactionId(transaction.getId());
            if(transactionPA != null){
                transactionPAList.add(transactionPA);
            }
        }
        return transactionPAList;
    }
}
