package com.mask.mask.service;

import com.itextpdf.text.DocumentException;
import com.mask.mask.entity.Transaction;
import com.mask.mask.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Month;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    public String saveTransaction(Transaction transaction) throws ParseException, IOException, MessagingException, DocumentException;
    public void purchase (String idTransaction) throws IOException, DocumentException, MessagingException;
    public String getNotifiedPA (String voucherCode, String categoryId);
    public String getNotifiedTravel (String voucherCode, String ptId);
    public void updateTransaction(String id, Transaction transaction);
    public void deleteTransactionById(String id);
    public Page<Transaction> getAllTransactionPerPage(Pageable pageable);
    public List<Transaction> getAllTransaction();
    public Transaction getTransactionById(String id);
    public List<Transaction> getTransactionByUserId(String id);
    public List getTransactionByStatusPromo(String statusPromo);

    public List getTransactionByPolisStatus(String statusPolis);
    public List getTransactionByPolisStatusandPeriode(String statusPolis, Integer periode);

    public List getTransactionByPolisStatusandPeriode2(String statusPolis, Date date1, Date date2);

    public void cancelTransaction(String id);

}
