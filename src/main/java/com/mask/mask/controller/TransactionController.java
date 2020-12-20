package com.mask.mask.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itextpdf.text.DocumentException;
import com.mask.mask.entity.Transaction;
import com.mask.mask.entity.Users;
import com.mask.mask.service.CreatePolisService;
import com.mask.mask.service.EmailSenderCore;
//import com.mask.mask.service.PdfGenaratorUtil;
import com.mask.mask.service.TransactionService;
import com.mask.mask.service.UsersService;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody Transaction transaction) throws ParseException, IOException, DocumentException, MessagingException {
        String result = transactionService.saveTransaction(transaction);
        return result;
    }

    @PostMapping("/purchase")
    public void purchase(@RequestParam(name = "id") String id) throws ParseException, IOException, DocumentException, MessagingException {
        transactionService.purchase(id);
    }

    @PutMapping("/transaction/{id}")
    public void updateTransaction(@PathVariable String id,  @RequestBody Transaction transaction) {
        transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/transaction")
    public void deleteTransactionById(@RequestParam(name = "id") String id) {
        transactionService.deleteTransactionById(id);
    }

    @GetMapping("/transactions")
    public Page<Transaction> getTransactionsPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        return transactionService.getAllTransactionPerPage(pageable);
    }

    @GetMapping("/transaction/list")
    public List<Transaction> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable(name = "id") String id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/transactionuser/{id}")
    public List<Transaction> getTransactionByUserId(@PathVariable(name = "id") String id) {
        return transactionService.getTransactionByUserId(id);
    }

    @GetMapping("/transactionsByPolis/{statusPolis}")
    public List getTransactionByPolisStatus(@PathVariable(name = "statusPolis") String statusPolis) {
        List transactionByPolis = transactionService.getTransactionByPolisStatus(statusPolis);
        return transactionByPolis;
    }

    @GetMapping("/transactionsByPolisperPeriode")
    public List getTransactionByPolisStatus(@RequestParam(name = "statusPolis") String statusPolis,
                                            @RequestParam Integer periode) {
        List transactionByPolisandPeriode = transactionService.getTransactionByPolisStatusandPeriode(statusPolis, periode);
        return transactionByPolisandPeriode;
    }

    @GetMapping("/transactionsPolisPeriod")
    public List getTransactionPolisPeriod(@RequestParam(name = "statusPolis") String statusPolis,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
        List transactionByPolisPeriode = transactionService.getTransactionByPolisStatusandPeriode2(statusPolis, date1, date2);
        return transactionByPolisPeriode;
    }

    @GetMapping("/transactionsByPromo/{statusPromo}")
    public List getTransactionByPromo(@PathVariable(name = "statusPromo") String statusPromo) {
        return transactionService.getTransactionByStatusPromo(statusPromo);
    }

    @GetMapping("/checkVoucherPA")
    public String getNotifiedVoucherPA (@RequestParam(name = "id") String id, @RequestParam(name = "voucher") String voucher){
       String result = transactionService.getNotifiedPA(voucher, id);
       return result;
    }

    @GetMapping("/checkVoucherTravel")
    public String getNotifiedVoucherTravel (@RequestParam(name = "id") String id, @RequestParam(name = "voucher") String voucher){
        String result = transactionService.getNotifiedTravel(voucher, id);
        return result;
    }

    @PutMapping("/transactioncanceled/{id}")
    public void cancelTransaction(@PathVariable String id){
        transactionService.cancelTransaction(id);
    }
}
