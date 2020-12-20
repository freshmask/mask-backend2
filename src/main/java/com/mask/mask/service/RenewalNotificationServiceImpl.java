package com.mask.mask.service;

import com.mask.mask.entity.*;
import org.joda.time.Days;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class RenewalNotificationServiceImpl {

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    TransactionPAService transactionPAService;

    @Autowired
    TransactionTravelService transactionTravelService;

    @Autowired
    TransactionPARService transactionPARService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UsersService usersService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void sendRenewalNotification() throws IOException, MessagingException {
        List<TransactionPA> transactionPAS = transactionPAService.getAllTransactionPA();
        List<TransactionTravel> transactionTravels = transactionTravelService.getAllTransactionTravel();
        List<TransactionPAR> transactionPARS = transactionPARService.getAllTransactionPAR();

        DateTime currentDate = new DateTime();
        if (transactionPAS != null){
            for (TransactionPA transactionPA:transactionPAS) {
                DateTime expDate = new DateTime(transactionPA.getExpDate());
                Integer diffdays = Days.daysBetween(currentDate, expDate).getDays();
                if (diffdays == 30 || diffdays == 7){
                    Transaction transaction = transactionService.getTransactionById(transactionPA.getTransaction().getId());
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    emailSenderCore.notifPARenewal(users, transactionPA);
                }
            }
        }

        if (transactionTravels != null){
            for (TransactionTravel transactionTravel:transactionTravels) {
                DateTime expDate = new DateTime(transactionTravel.getExpDate());
                Integer diffdays = Days.daysBetween(currentDate, expDate).getDays();
                if (diffdays == 30 || diffdays == 7){
                    Transaction transaction = transactionService.getTransactionById(transactionTravel.getTransaction().getId());
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    emailSenderCore.notifTravelRenewal(users, transactionTravel);
                }
            }
        }

        if (transactionPARS != null){
            for (TransactionPAR transactionPAR:transactionPARS) {
                DateTime expDate = new DateTime(transactionPAR.getExpDate());
                Integer diffdays = Days.daysBetween(currentDate, expDate).getDays();
                if (diffdays == 30 || diffdays == 7){
                    Transaction transaction = transactionService.getTransactionById(transactionPAR.getTransaction().getId());
                    Users users = usersService.getUsersById(transaction.getUsers().getId());
                    emailSenderCore.notifPARRenewal(users, transactionPAR);
                }
            }
        }
    }
}
