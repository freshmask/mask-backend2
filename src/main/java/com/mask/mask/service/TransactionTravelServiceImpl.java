package com.mask.mask.service;

import com.mask.mask.entity.*;
import com.mask.mask.exception.DataNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.TransactionTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionTravelServiceImpl implements TransactionTravelService {

    @Autowired
    TransactionTravelRepository transactionTravelRepository;

    @Autowired
    TransactionService transactionService;

    @Override
    public void saveTransactionTravel(TransactionTravel transactionTravel) {
        TransactionTravel transactionTravel1 = transactionTravelRepository.save(transactionTravel);
        CustomerTravel customerTravel = transactionTravel.getCustomerTravel();
        customerTravel.setTransactionTravel(transactionTravel1);
        transactionTravelRepository.save(transactionTravel);
    }

    @Override
    public void updateTransactionTravel(String id, TransactionTravel transactionTravel) {
        if (!transactionTravelRepository.existsById(transactionTravel.getId())){
            throw new DataNotFoundException(String.format(DataNotFoundException.DATA_NOT_FOUND,transactionTravel.getClass(),transactionTravel.getId()));
        }
        transactionTravelRepository.save(transactionTravel);
    }

    @Override
    public void deleteTransactionTravelById(String id) {
        transactionTravelRepository.deleteById(id);
    }

    @Override
    public Page<TransactionTravel> getAllTransactionTravelPerPage(Pageable pageable) {
        return transactionTravelRepository.findAll(pageable);
    }

    @Override
    public List<TransactionTravel> getAllTransactionTravel() {
        return transactionTravelRepository.findAll();
    }

    @Override
    public TransactionTravel getTransactionTravelById(String id) {
        Optional<TransactionTravel> transactionTravelOptional = transactionTravelRepository.findById(id);;
        if (transactionTravelOptional.isPresent()){
            return transactionTravelOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,TransactionTravel.class,id));
    }

    @Override
    public List<TransactionTravel> findTransactionTravelByPackageTravelPtId(String ptId) {
        return transactionTravelRepository.findTransactionTravelByPackageTravelPtId(ptId);
    }

    @Override
    public List<TransactionTravel> findTransactionTravelByIsPromo(String isPromo) {
        return transactionTravelRepository.findTransactionTravelByIsPromo(isPromo);
    }

    @Override
    public TransactionTravel getransactionTravelByTransactionId(String id) {
        return transactionTravelRepository.findTransactionTravelByTransactionId(id);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void changeStatusPolisTravelScheduled (){
        List<TransactionTravel> transactionTravels = transactionTravelRepository.findAllByStatusPolis("Belum berlaku");
        Date currentDate = new Date();
        for (TransactionTravel transactionTravel:transactionTravels) {
            if (((currentDate.after(transactionTravel.getStartDate())) && (currentDate.before(transactionTravel.getExpDate()))) || currentDate.equals(transactionTravel.getStartDate())){
                transactionTravel.setStatusPolis("aktif");
                transactionTravelRepository.save(transactionTravel);
            }
        }
    }

    @Override
    public List<TransactionTravel> getTransactionTravelByUserId(String userID) {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userID);
        List<TransactionTravel> transactionTravelList = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            TransactionTravel transactionTravel = transactionTravelRepository.findTransactionTravelByTransactionId(transaction.getId());
            if(transactionTravel != null){
                transactionTravelList.add(transactionTravel);
            }
        }
        return transactionTravelList;
    }
}
