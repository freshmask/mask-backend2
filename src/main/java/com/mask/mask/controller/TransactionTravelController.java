package com.mask.mask.controller;

import com.mask.mask.entity.TransactionPA;
import com.mask.mask.entity.TransactionTravel;
import com.mask.mask.service.TransactionTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionTravelController {

    @Autowired
    TransactionTravelService transactionTravelService;


    @PostMapping("/transactiontravel")
    public void saveTransactionTravel(@RequestBody TransactionTravel transactionTravel){
        transactionTravelService.saveTransactionTravel(transactionTravel);
    }

    @PutMapping("/transactiontravel/{id}")
    public void updateTransactionTravel(@PathVariable String id, @RequestBody TransactionTravel transactionTravel){
        transactionTravelService.updateTransactionTravel(id, transactionTravel);
    }

    @DeleteMapping("/transactiontravel")
    public void deleteTransactionTravelById(@RequestParam(name = "id")String id){
        transactionTravelService.deleteTransactionTravelById(id);
    }

    @GetMapping("/transactiontravel/list")
    public List<TransactionTravel> getAllTransactionTravel(){
        return transactionTravelService.getAllTransactionTravel();
    }

    @GetMapping("/transactiontravel")
    public Page<TransactionTravel> getTransactionTravelPerPage(@RequestParam(name="page", defaultValue = "0") Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return transactionTravelService.getAllTransactionTravelPerPage(pageable);
    }

    @GetMapping("/transactiontravel/{id}")
    public TransactionTravel getTransactionTravelById(@PathVariable(name = "id") String id){
        return transactionTravelService.getTransactionTravelById(id);
    }

    @GetMapping("/transactiontravelbypackagetravel/{id}")
    public List<TransactionTravel> getTransactionTravelByPackageId(@PathVariable String id){
        return transactionTravelService.findTransactionTravelByPackageTravelPtId(id);
    }

    @GetMapping("/transactionTravelByPromo/{isPromo}")
    public List<TransactionTravel> getTransactionTravelByPromo(@PathVariable String isPromo){
        return transactionTravelService.findTransactionTravelByIsPromo(isPromo);
    }

    @GetMapping("/transactionTravelByUser/{id}")
    public List<TransactionTravel> getTransactionTravelByUser(@PathVariable String id){
        return transactionTravelService.getTransactionTravelByUserId(id);
    }

}
