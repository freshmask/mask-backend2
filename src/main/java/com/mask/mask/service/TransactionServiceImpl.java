package com.mask.mask.service;

import com.itextpdf.text.DocumentException;
import com.mask.mask.entity.*;
import com.mask.mask.exception.*;
import com.mask.mask.repository.TransactionRepository;
import org.bouncycastle.util.Pack;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryPAService categoryPAService;

    @Autowired
    PackageTravelService packageTravelService;

    @Autowired
    ProductService productService;

    @Autowired
    CreatePolisService createPolisService;

    @Autowired
    EmailSenderCore emailSenderCore;

    @Autowired
    TransactionTravelService transactionTravelService;

    @Autowired
    TransactionPARService transactionPARService;

    @Autowired
    TransactionPAService transactionPAService;

    @Autowired
    CustomerPAService customerPAService;

    @Autowired
    CustomerPARService customerPARService;

    @Autowired
    CustomerTravelService customerTravelService;

    @Override
    public String saveTransaction(Transaction transaction) throws ParseException, IOException, MessagingException, DocumentException {
        Float coverageValueInitiate = 0.0f;
        String result = "";
        Float premi = 0f;
        if (transaction.getTransactionPAR() != null ) {
            coverageValueInitiate = transaction.getTransactionPAR().getCustomerPAR().getBuildingCoverageValue() + transaction.getTransactionPAR().getCustomerPAR().getMachineCoverageValue() + transaction.getTransactionPAR().getCustomerPAR().getFurnitureCoverageValue();
        }
        if (((transaction.getTransactionPAR() != null) && (coverageValueInitiate <= 5000000000.0f)) || (transaction.getTransactionPA() != null) || (transaction.getTransactionTravel() != null)) {
            transaction.setVersion(0);
            Transaction transaction1 = transactionRepository.save(transaction);
            TransactionPA transactionPA = transaction1.getTransactionPA();
            TransactionTravel transactionTravel = transaction1.getTransactionTravel();
            TransactionPAR transactionPAR = transaction1.getTransactionPAR();
            if (transactionPA != null) {
                TransactionPA transactionPA1 = transactionPAService.getTransactionPAById(transactionPA.getTrxpaId());
                transactionPA1.setStartDate(cal.getTime());
                transactionPA1.setAdminFee(0.0f);
                CategoryPA categoryPA = categoryPAService.getCategoryPAById(transaction.getTransactionPA().getCategoryPA().getCategoryId());
                Integer days = categoryPA.getDays();
                cal.add(Calendar.DAY_OF_MONTH, days);
                transactionPA1.setExpDate(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, -days);
                CustomerPA customerPA = transactionPA.getCustomerPA();
                if (customerPA.getVoucherPA().equalsIgnoreCase(categoryPA.getVoucher())) {
                    transactionPA1.setPremi(categoryPA.getPricePromo() + transactionPA.getAdminFee());
                    transactionPA1.setIsPromo("true");
                } else {
                    transactionPA1.setPremi(categoryPA.getPrice() + transactionPA.getAdminFee());
                    transactionPA1.setIsPromo("false");
                }
                transactionPA1.setIsPayment("Belum Bayar");
                transactionPA1.setIsClaim("false");
                transactionPA1.setStatusClaim("Belum diklaim");
                transactionPA1.setStatusPolis("tidak aktif");
                transactionPA1.setTransaction(transaction1);
                premi = transactionPA1.getPremi();
                String premiResult = String.format("%.0f", premi);
                result = transaction.getId() + "," + premiResult;
                transactionPAService.addTransactionPA(transactionPA1);
            } else if (transactionTravel != null) {
                TransactionTravel transactionTravel1 = transactionTravelService.getTransactionTravelById(transactionTravel.getId());
                CustomerTravel customerTravel = transactionTravel.getCustomerTravel();
                transactionTravel1.setStartDate(customerTravel.getDepartureDate());
                transactionTravel1.setAdminFee(0.0f);
                PackageTravel packageTravel = packageTravelService.getPackageTravelById(transaction.getTransactionTravel().getPackageTravel().getPtId());
                Integer travelDays = packageTravel.getDays();
                cal.setTime(customerTravel.getDepartureDate());
                cal.add(Calendar.DAY_OF_MONTH, travelDays);
                transactionTravel1.setExpDate(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, -travelDays);
                if (customerTravel.getVoucherTravel().equalsIgnoreCase(packageTravel.getVoucher())) {
                    transactionTravel1.setPremi(packageTravel.getPricePromo() + transactionTravel.getAdminFee());
                    transactionTravel1.setIsPromo("true");
                } else {
                    transactionTravel.setPremi(packageTravel.getPrice() + transactionTravel.getAdminFee());
                    transactionTravel.setIsPromo("false");
                }
                transactionTravel1.setIsPayment("Belum Bayar");
                transactionTravel1.setIsClaim("false");
                transactionTravel1.setStatusClaim("Belum diklaim");
                transactionTravel1.setStatusPolis("tidak aktif");
                transactionTravel1.setTransaction(transaction1);
                premi = transactionTravel1.getPremi();
                String premiResult = String.format("%.0f", premi);
                result = transaction.getId() + "," + premiResult;
                transactionTravelService.saveTransactionTravel(transactionTravel1);
            } else if (transactionPAR != null) {
                TransactionPAR transactionPAR1 = transactionPARService.getTransactionPARById(transactionPAR.getTrxparId());
                transactionPAR1.setCustomerPAR(transactionPAR.getCustomerPAR());
                transactionPAR1.setStartDate(cal.getTime());
                transactionPAR1.setAdminFee(12000f);
                Product product = productService.getProductById(transaction.getTransactionPAR().getProduct().getProductId());
                CustomerPAR customerPAR = transactionPAR.getCustomerPAR();
                cal.add(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
                transactionPAR.setExpDate(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, -cal.getActualMaximum(Calendar.DAY_OF_YEAR));
                transactionPAR1.setIsPayment("Belum Bayar");
                transactionPAR1.setIsClaim("false");
                transactionPAR1.setStatusClaim("Belum diklaim");
                transactionPAR1.setStatusPolis("tidak aktif");
                Float buildingCoverageValue = customerPAR.getBuildingCoverageValue();
                Float machineCoverageValue = customerPAR.getMachineCoverageValue();
                Float furnitureCoverageValue = customerPAR.getFurnitureCoverageValue();
                Float adminFee = transactionPAR.getAdminFee();
                Float discount = product.getDiscount();
                if (discount == null) {
                    discount = 0f;
                    transactionPAR1.setIsPromo("false");
                } else if (discount != null) {
                    transactionPAR1.setIsPromo("true");
                }
                Float totalCoverage = (buildingCoverageValue + furnitureCoverageValue + machineCoverageValue);
                if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("1")) {
                    transactionPAR1.setPremi((0.294f / 1000f * totalCoverage) + adminFee - discount);
                } else if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("2")) {
                    transactionPAR1.setPremi((0.397f / 1000f * totalCoverage) + adminFee - discount);
                } else if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("3")) {
                    transactionPAR1.setPremi((0.499f / 1000f * totalCoverage) + adminFee - discount);
                } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("1")) {
                    transactionPAR1.setPremi((0.478f / 1000f * totalCoverage) + adminFee - discount);
                } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("2")) {
                    transactionPAR1.setPremi((0.645f / 1000f * totalCoverage) + adminFee - discount);
                } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("3")) {
                    transactionPAR1.setPremi((0.812f / 1000f * totalCoverage) + adminFee - discount);
                }
                transactionPAR1.setTransaction(transaction1);
                premi = transactionPAR1.getPremi();
                String premiResult = String.format("%.0f", premi);
                result = transaction.getId() + "," + premiResult;
                transactionPARService.addTransactionPAR(transactionPAR1);
            }
        } else {
            throw new LimitOfCompensationException("Nilai Pertanggungan yang anda masukkan lebih dari nilai maksimal pertanggungan sebesar Rp 5.000.000.000,-");
        }
        return result;
    }

    @Override
    public void purchase(String idTransaction) throws IOException, DocumentException, MessagingException {
        Transaction transaction = transactionRepository.findById(idTransaction).get();
        TransactionPA transactionPA = transactionPAService.getransactionPAByTransactionId(transaction.getId());
        TransactionTravel transactionTravel = transactionTravelService.getransactionTravelByTransactionId(transaction.getId());
        TransactionPAR transactionPAR = transactionPARService.getTransactionPARByTransactionId(transaction.getId());
        if (transactionPA != null) {
            if (transactionPA.getIsPayment().equalsIgnoreCase("Belum Bayar")) {
                CustomerPA customerPA = transactionPA.getCustomerPA();
                transactionPA.setIsPayment("Lunas");
                transactionPA.setStatusPolis("aktif");
                transactionPAService.updateTransactionPA(transactionPA);
                createPolisService.createPolisPA(transactionPA, customerPA);
                emailSenderCore.sendPolisPA(transactionPA, customerPA);
                return;
            } else{
                throw new PurchasedException("Transaksi Anda Sudah Lunas");
            }
        }
        if (transactionTravel != null) {
            if (transactionTravel.getIsPayment().equalsIgnoreCase("Belum Bayar")) {
                CustomerTravel customerTravel = transactionTravel.getCustomerTravel();
                transactionTravel.setIsPayment("Lunas");
                Date currentDate = new Date();
                if(currentDate.before(transactionTravel.getStartDate())){
                    transactionTravel.setStatusPolis("belum berlaku");
                } else if ((currentDate.after(transactionTravel.getStartDate())) || (currentDate.equals(transactionTravel.getStartDate()))){
                    transactionTravel.setStatusPolis("aktif");
                }
                transactionTravelService.updateTransactionTravel(transactionTravel.getId(), transactionTravel);
                createPolisService.createPolisTravel(transactionTravel, customerTravel);
                emailSenderCore.sendPolisTravel(transactionTravel, customerTravel);
                return;
            } else{
                throw new PurchasedException("Transaksi Anda Sudah Lunas");
            }
        }
        if (transactionPAR != null) {
            if (transactionPAR.getIsPayment().equalsIgnoreCase("Belum Bayar")) {
                CustomerPAR customerPAR = transactionPAR.getCustomerPAR();
                transactionPAR.setIsPayment("Lunas");
                transactionPAR.setStatusPolis("aktif");
                transactionPARService.updateTransactionPAR(transactionPAR);
                createPolisService.createPolisPAR(transactionPAR, customerPAR);
                emailSenderCore.sendPolisPAR(transactionPAR, customerPAR);
                return;
            } else{
                throw new PurchasedException("Transaksi Anda Sudah Lunas");
            }
        }
    }

    @Override
    public String getNotifiedPA (String voucherCode, String categoryId) {
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(categoryId);
        if (!categoryPA.getVoucher().equalsIgnoreCase(voucherCode)){
            throw new VoucherCodeDoesntMatchException("Kode Voucher Tidak Sesuai");
        }else {
            return "Kode Voucher Sudah Sesuai";
        }
    }

    @Override
    public String getNotifiedTravel(String voucherCode, String ptId) {
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(ptId);
        if (!packageTravel.getVoucher().equalsIgnoreCase(voucherCode)){
            throw new VoucherCodeDoesntMatchException("Kode Voucher Tidak Sesuai");
        }else {
            return "Kode Voucher Sudah Sesuai";
        }
    }

    @Override
    public void updateTransaction(String id, Transaction transaction) {
        if (!transactionRepository.existsById(transaction.getId())) {
            throw new DataNotFoundException(String.format(DataNotFoundException.DATA_NOT_FOUND, transaction.getClass(), transaction.getId()));
        } else {
            Transaction transaction1 = transactionRepository.findById(transaction.getId()).get();
            transaction.setVersion(transaction1.getVersion() + 1);
            transactionRepository.save(transaction);
        }

    }

    @Override
    public void deleteTransactionById(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Page<Transaction> getAllTransactionPerPage(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        for (Transaction transaction: transactions) {
            TransactionPA transactionPA = transaction.getTransactionPA();
            TransactionTravel transactionTravel= transaction.getTransactionTravel();
            TransactionPAR transactionPAR = transaction.getTransactionPAR();
            if(transactionPA!=null){
                if(transactionPA.getExpDate().before(new Date())){
                    transactionPA.setStatusPolis("tidak aktif");
                }
            } else if (transactionPAR!=null){
                if(transactionPAR.getExpDate().before(new Date())){
                    transactionPAR.setStatusPolis("tidak aktif");
                }
            } else if (transactionTravel!=null){
                if(transactionTravel.getExpDate().before(new Date())){
                    transactionTravel.setStatusPolis("tidak aktif");
                }
            }
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction transaction: transactionList) {
            TransactionPA transactionPA = transaction.getTransactionPA();
            TransactionTravel transactionTravel= transaction.getTransactionTravel();
            TransactionPAR transactionPAR = transaction.getTransactionPAR();
            if(transactionPA!=null){
                if(transactionPA.getExpDate().before(new Date())){
                    transactionPA.setStatusPolis("tidak aktif");
                }
            } else if (transactionPAR!=null){
                if(transactionPAR.getExpDate().before(new Date())){
                    transactionPAR.setStatusPolis("tidak aktif");
                }
            } else if (transactionTravel!=null){
                if(transactionTravel.getExpDate().before(new Date())){
                    transactionTravel.setStatusPolis("tidak aktif");
                }
            }
        }
        return transactionList;
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> getTransactionByUserId(String id) {
        return transactionRepository.findTransactionByUsersId(id);
    }

    @Override
    public List getTransactionByPolisStatus(String statusPolis) {
        List<TransactionPA> transactionPAS = transactionPAService.getAllTransactionPA();
        List<TransactionTravel> transactionTravels = transactionTravelService.getAllTransactionTravel();
        List<TransactionPAR> transactionPARS = transactionPARService.getAllTransactionPAR();
        List list = new ArrayList();
        for (TransactionPA transactionPA:transactionPAS) {
            if (transactionPA.getStatusPolis().equalsIgnoreCase(statusPolis)) {
                list.add(transactionPA);
            }
        }
        for (TransactionTravel transactionTravel:transactionTravels) {
            if (transactionTravel.getStatusPolis().equalsIgnoreCase(statusPolis)) {
                list.add(transactionTravel);
            }
        }
        for (TransactionPAR transactionPAR:transactionPARS) {
            if (transactionPAR.getStatusPolis().equalsIgnoreCase(statusPolis)) {
                list.add(transactionPAR);
            }
        }
        return list;
    }


    @Override
    public List getTransactionByPolisStatusandPeriode(String statusPolis, Integer periode) {
        List<TransactionPA> transactionPAS = transactionPAService.getAllTransactionPA();
        List<TransactionTravel> transactionTravels = transactionTravelService.getAllTransactionTravel();
        List<TransactionPAR> transactionPARS = transactionPARService.getAllTransactionPAR();
        List newList = new ArrayList();
        for (TransactionPA transactionPA:transactionPAS) {
            Integer monthPA = transactionPA.getStartDate().getMonth();
            if (transactionPA.getStatusPolis().equalsIgnoreCase(statusPolis) && monthPA.equals(periode-1)) {
                newList.add(transactionPA);
            }
        }
        for (TransactionTravel transactionTravel:transactionTravels) {
            Integer monthTravel = transactionTravel.getStartDate().getMonth();
            if (transactionTravel.getStatusPolis().equalsIgnoreCase(statusPolis) && monthTravel.equals(periode-1)) {
                newList.add(transactionTravel);
            }
        }
        for (TransactionPAR transactionPAR:transactionPARS) {
            Integer monthPAR = transactionPAR.getStartDate().getMonth();
            if (transactionPAR.getStatusPolis().equalsIgnoreCase(statusPolis) && monthPAR.equals(periode-1)) {
                newList.add(transactionPAR);
            }
        }
        return newList;
    }


    @Override
    public List getTransactionByPolisStatusandPeriode2(String statusPolis, Date date1, Date date2) {
        System.out.println("Ini date 1" + date1);
        System.out.println("Ini date 1" + date2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        calendar.add(Calendar.HOUR_OF_DAY, 23);
        Date newDate2 = calendar.getTime();
        System.out.println("Ini new date 2" + newDate2);
        List<TransactionPA> transactionPAS = transactionPAService.getAllTransactionPA();
        List<TransactionTravel> transactionTravels = transactionTravelService.getAllTransactionTravel();
        List<TransactionPAR> transactionPARS = transactionPARService.getAllTransactionPAR();
        List resultList = new ArrayList();
        for (TransactionPA transactionPA:transactionPAS) {
            if (transactionPA.getStatusPolis().equalsIgnoreCase(statusPolis) && ((transactionPA.getStartDate().equals(date1) || transactionPA.getStartDate().after(date1))&& (transactionPA.getStartDate().before(newDate2)))) {
                resultList.add(transactionPA);
            }
        }
        for (TransactionTravel transactionTravel:transactionTravels) {
            if (transactionTravel.getStatusPolis().equalsIgnoreCase(statusPolis) && ((transactionTravel.getStartDate().equals(date1) || transactionTravel.getStartDate().after(date1)) && (transactionTravel.getStartDate().before(newDate2)))) {
                resultList.add(transactionTravel);
            }
        }
        for (TransactionPAR transactionPAR:transactionPARS) {
            if (transactionPAR.getStatusPolis().equalsIgnoreCase(statusPolis) && ((transactionPAR.getStartDate().equals(date1) || transactionPAR.getStartDate().after(date1)) && (transactionPAR.getStartDate().before(newDate2)))) {
                resultList.add(transactionPAR);
            }
        }
        return resultList;
    }

    @Override
    public List getTransactionByStatusPromo(String statusPromo) {
        List<TransactionPA> transactionPAS = transactionPAService.getAllTransactionPA();
        List<TransactionTravel> transactionTravels = transactionTravelService.getAllTransactionTravel();
        List<TransactionPAR> transactionPARS = transactionPARService.getAllTransactionPAR();
        List listTransByPromo = new ArrayList();
        for (TransactionPA transactionPA:transactionPAS) {
            if (transactionPA.getIsPromo().equalsIgnoreCase(statusPromo)) {
                listTransByPromo.add(transactionPA);
            }
        }
        for (TransactionTravel transactionTravel:transactionTravels) {
            if (transactionTravel.getIsPromo().equalsIgnoreCase(statusPromo)) {
                listTransByPromo.add(transactionTravel);
            }
        }
        for (TransactionPAR transactionPAR:transactionPARS) {
            if (transactionPAR.getIsPromo().equalsIgnoreCase(statusPromo)) {
                listTransByPromo.add(transactionPAR);
            }
        }
        return listTransByPromo;
    }


    @Override
    public void cancelTransaction(String id) {
        Transaction transaction = transactionRepository.findById(id).get();
        TransactionPA transactionPA = transaction.getTransactionPA();
        TransactionPAR transactionPAR = transaction.getTransactionPAR();
        TransactionTravel transactionTravel = transaction.getTransactionTravel();
        if (transactionPA!= null){
            transaction.getTransactionPA().setIsPayment("batal");
            transactionRepository.save(transaction);
        } else if (transactionPAR!=null){
            transaction.getTransactionPAR().setIsPayment("batal");
            transactionRepository.save(transaction);
        } else if (transactionTravel!= null){
            transaction.getTransactionTravel().setIsPayment("batal");
            transactionRepository.save(transaction);
        }
    }

}
