package com.mask.mask.controller;

import com.mask.mask.dto.TransactionPARSearchDTO;
import com.mask.mask.entity.TransactionPA;
import com.mask.mask.entity.TransactionPAR;
import com.mask.mask.service.TransactionPARService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionPARController {

    @Autowired
    TransactionPARService transactionPARService;

    @PostMapping("/transactionPAR")
    public void addTransactionPAR(@RequestBody TransactionPAR transactionPAR){
        transactionPARService.addTransactionPAR(transactionPAR);
    }

    @GetMapping("/transactionsPAR")
    public Page<TransactionPAR> searchTransactionPAR (@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                                    @RequestParam(name = "isPayment", required = false) String isPayment,
                                                    @RequestParam(name = "isClaim", required = false) String isClaim,
                                                    @RequestParam(name = "premi", required = false) Float premi,
                                                    @RequestParam(name = "statusPolis", required = false) String statusPolis){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        TransactionPARSearchDTO transactionPARSearchDTO = new TransactionPARSearchDTO(isPayment, isClaim, premi, statusPolis);
        return transactionPARService.getTransactionPARInPage(pageable, transactionPARSearchDTO);
    }

    @GetMapping("/transactionPAR/list")
    public List<TransactionPAR> getAllListTransactionPAR (){
        return transactionPARService.getAllTransactionPAR();
    }

    @GetMapping("/transactionPAR/{trxparId}")
    public TransactionPAR getTransactionPAR (@PathVariable(name = "trxparId") String trxparId){
        TransactionPAR transactionPAR = transactionPARService.getTransactionPARById(trxparId);
        return transactionPAR;
    }

    @PutMapping("/transactionPAR")
    public void updateTransactionPAR (@RequestBody TransactionPAR transactionPAR){
        transactionPARService.updateTransactionPAR(transactionPAR);
    }

    @DeleteMapping("/transactionPAR")
    public void deleteTransactionPARyId (@RequestParam(name = "trxparId") String trxparId){
        transactionPARService.deleteTransactionPARById(trxparId);
    }

    @GetMapping("transactionparbyproduct/{id}")
    public List<TransactionPAR> getTransactionPARByProductId (@PathVariable String id){
        return transactionPARService.getTransactionPARByProductProductId(id);
    }

    @GetMapping("/transactionPARByPromo/{isPromo}")
    public List<TransactionPAR> getTransactionPARByPromo(@PathVariable String isPromo){
        return transactionPARService.findTransactionPARByIsPromo(isPromo);
    }

    @GetMapping("/transactionPARByUser/{id}")
    public List<TransactionPAR> getTransactionPARByUser(@PathVariable String id){
        return transactionPARService.getTransactionPARByUserId(id);
    }
}
