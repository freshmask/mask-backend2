package com.mask.mask.controller;

import com.mask.mask.dto.TransactionPASearchDTO;
import com.mask.mask.entity.TransactionPA;
import com.mask.mask.service.TransactionPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionPAController {

    @Autowired
    TransactionPAService transactionPAService;

    @PostMapping("/transactionPA")
    public void addtransactionPA(@RequestBody TransactionPA transactionPA){
        transactionPAService.addTransactionPA(transactionPA);
    }

    @GetMapping("/transactionsPA")
    public Page<TransactionPA> searchTransactionPA (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                    @RequestParam(name = "isPayment", required = false) String isPayment,
                                                    @RequestParam(name = "isClaim", required = false) String isClaim,
                                                    @RequestParam(name = "premi", required = false) Float premi,
                                                    @RequestParam(name = "statusPolis", required = false) String statusPolis,
                                                    @RequestParam(name = "startDate", required = false) Date startDate,
                                                    @RequestParam(name = "endDate", required = false) Date endDate){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        TransactionPASearchDTO transactionPASearchDTO = new TransactionPASearchDTO(isPayment, isClaim, premi, statusPolis, startDate, endDate);
        return transactionPAService.getTransactionPAInPage(pageable, transactionPASearchDTO);
    }

    @GetMapping("/transactionsPA/list")
    public List<TransactionPA> getAllListTransactionPA (){
        return transactionPAService.getAllTransactionPA();
    }

    @GetMapping("/transactionPA/{trxpaId}")
    public TransactionPA getTransactionPA(@PathVariable(name = "trxpaId") String trxpaId){
        TransactionPA transactionPA = transactionPAService.getTransactionPAById(trxpaId);
        return transactionPA;
    }

    @PutMapping("/transactionPA")
    public void updateTransactionPA(@RequestBody TransactionPA transactionPA){
        transactionPAService.updateTransactionPA(transactionPA);
    }

    @DeleteMapping("/transactionPA")
    public void deleteTransactionPAById (@RequestParam(name = "trxpaId") String trxpaId){
        transactionPAService.deleteTransactionPAById(trxpaId);
    }

    @GetMapping("/transactionPAByCategory/{id}")
    public List<TransactionPA> getTransactionPAByCategoryId(@PathVariable String id){
       return transactionPAService.getTransactionPAByCategoryPAId(id);
    }

    @GetMapping("/transactionPAByPromo/{isPromo}")
    public List<TransactionPA> getTransactionPAByPromo(@PathVariable String isPromo){
        return transactionPAService.findTransactionPAByIsPromo(isPromo);
    }

    @GetMapping("/transactionPAByUser/{id}")
    public List<TransactionPA> getTransactionPAByUser(@PathVariable String id){
        return transactionPAService.getTransactionPAByUserId(id);
    }
}
