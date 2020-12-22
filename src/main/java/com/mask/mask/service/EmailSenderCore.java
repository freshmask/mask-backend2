package com.mask.mask.service;

import com.mask.mask.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderCore {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    CategoryPAService categoryPAService;

    @Autowired
    PackageTravelService packageTravelService;

    @Autowired
    ProductService productService;

    @Autowired
    PackagePAService packagePAService;

    @Autowired
    UsersService usersService;

    @Autowired
    TransactionPAService transactionPAService;

    @Autowired
    TransactionTravelService transactionTravelService;

    @Autowired
    TransactionPARService transactionPARService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerPARService customerPARService;

    @Autowired
    CustomerPAService customerPAService;

    @Autowired
    CustomerTravelService customerTravelService;


    public void contextLoads(Users users, SecureToken secureToken) {
        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("M-ASK Account Verification");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());
        model.put("tokens", secureToken.getConfirmToken());

        emailSenderService.sendVerfMessage(mail, model);
    }

    public void forgotPassLoads(Users users, String tempToken) {
        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("[M-ASK] Your New Temporary Password");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());
        model.put("email", users.getEmail());
        model.put("password", tempToken);

        emailSenderService.sendForgotMessage(mail, model);
    }

    public void notifAccountActivatedLoads(Users users) {
        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("[M-ASK] Your Account has Activated");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());

        emailSenderService.sendNotifActivMessage(mail, model);
    }

    public void sendPolisPA(TransactionPA transactionPA, CustomerPA customerPA) {
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(transactionPA.getCategoryPA().getCategoryId());
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        Transaction transaction = transactionService.getTransactionById(transactionPA.getTransaction().getId());
        Users users = usersService.getUsersById(transaction.getUsers().getId());
        String[] emailBCC = new String[2];
        emailBCC[0] = customerPA.getEmail();
        emailBCC[1] = customerPA.getHeirEmail();

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setBcc(emailBCC);
        mail.setSubject("[M-ASK] Sertifikat Asuransi Kecelakaan Diri");

        Map<String, Object> model = new HashMap<>();
        model.put("name", customerPA.getName());
        model.put("productName", product.getProductName());
        model.put("polisNumber", transactionPA.getTrxpaId());

        String generatePolisNumber = transactionPA.getTrxpaId();

        emailSenderService.sendPolisPA(mail, model, generatePolisNumber);
    }

    public void sendPolisTravel(TransactionTravel transactionTravel, CustomerTravel customerTravel) {
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(transactionTravel.getPackageTravel().getPtId());
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());
        Transaction transaction = transactionService.getTransactionById(transactionTravel.getTransaction().getId());
        Users users = usersService.getUsersById(transaction.getUsers().getId());
        String[] emailBCC = new String[2];
        emailBCC[0] = customerTravel.getEmail();
        emailBCC[1] = customerTravel.getHeirEmail();

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setBcc(emailBCC);
        mail.setSubject("[M-ASK] Sertifikat Asuransi Perjalanan");

        String polisTravelNumber = transactionTravel.getId();

        Map<String, Object> model = new HashMap<>();
        model.put("name", customerTravel.getName());
        model.put("productName", product.getProductName());
        model.put("polisNumber", transactionTravel.getId());

        emailSenderService.sendPolisTravel(mail, model, polisTravelNumber);
    }

    public void sendPolisPAR(TransactionPAR transactionPAR, CustomerPAR customerPAR) {
        Product product = productService.getProductById(transactionPAR.getProduct().getProductId());
        Transaction transaction = transactionService.getTransactionById(transactionPAR.getTransaction().getId());
        Users users = usersService.getUsersById(transaction.getUsers().getId());
        String[] emailBCC = new String[1];
        emailBCC[0] = customerPAR.getEmail();

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setBcc(emailBCC);
        mail.setSubject("[M-ASK] Sertifikat Asuransi Property atau Harta Benda");

        String polisPARNumber = transactionPAR.getTrxparId();

        Map<String, Object> model = new HashMap<>();
        model.put("name", customerPAR.getName());
        model.put("productName", product.getProductName());
        model.put("polisNumber", transactionPAR.getTrxparId());

        emailSenderService.sendPolisPAR(mail, model, polisPARNumber);
    }

    public void sendNotifClaimPA(ClaimPA claimPA) {
        TransactionPA transactionPA = transactionPAService.getransactionPAByTransactionId(claimPA.getTransaction().getId());
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(transactionPA.getCategoryPA().getCategoryId());
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());
        String statusClaim = transactionPA.getStatusClaim();
        CustomerPA customerPA = customerPAService.getCustomerPAById(transactionPA.getCustomerPA().getCustomerpaId());
        String emailCustomer = customerPA.getEmail();

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(claimPA.getEmail());
        mail.setCc(emailCustomer);
        mail.setSubject("[M-ASK] Konfirmasi Klaim Asuransi Kecelakaan Diri");

        Map<String, Object> model = new HashMap<>();
        model.put("name", claimPA.getName());
        model.put("productName", product.getProductName());

        SimpleDateFormat formatIncident = new SimpleDateFormat("dd MMMM yyyy");
        String incidentDate = formatIncident.format(claimPA.getIncidentDate());
        model.put("incidentDate", incidentDate);

        String approveValue = String.format("%.0f", claimPA.getClaimApproval());
        model.put("claimApproval", approveValue);
        model.put("polisNumber", transactionPA.getTrxpaId());
        if (statusClaim.equalsIgnoreCase("disetujui")){
            model.put("approval", "Pengajuan permohonan klaim telah memenuhi persyaratan dan kami setujui dengan jumlah sebesar Rp " + approveValue);
        } else if (statusClaim.equalsIgnoreCase("ditolak")) {
            model.put("approval", "Pengajuan permohonan klaim belum memenuhi persyaratan sehingga tidak dapat diproses lebih lanjut");
        }

        emailSenderService.sendClaimMessage(mail, model);
    }

    public void sendNotifClaimTravel(ClaimTravel claimTravel) {
        TransactionTravel transactionTravel = transactionTravelService.getransactionTravelByTransactionId(claimTravel.getTransaction().getId());
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(transactionTravel.getPackageTravel().getPtId());
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());
        String statusClaim = transactionTravel.getStatusClaim();
        CustomerTravel customerTravel = customerTravelService.getCustomerTravelById(transactionTravel.getCustomerTravel().getCustomertravelId());
        String emailCustomer = customerTravel.getEmail();

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(claimTravel.getEmail());
        mail.setCc(emailCustomer);
        mail.setSubject("[M-ASK] Konfirmasi Klaim Asuransi Perjalanan");

        Map<String, Object> model = new HashMap<>();
        model.put("name", claimTravel.getName());
        model.put("productName", product.getProductName());

        SimpleDateFormat formatIncident = new SimpleDateFormat("dd MMMM yyyy");
        String incidentDate = formatIncident.format(claimTravel.getIncidentDate());
        model.put("incidentDate", incidentDate);

        String approveValue = String.format("%.0f", claimTravel.getClaimApproval());
        model.put("claimApproval", approveValue);
        model.put("polisNumber", transactionTravel.getId());
        if (statusClaim.equalsIgnoreCase("disetujui")){
            model.put("approval", "Pengajuan permohonan klaim telah memenuhi persyaratan dan kami setujui dengan jumlah sebesar Rp " + approveValue);
        } else if (statusClaim.equalsIgnoreCase("ditolak")) {
            model.put("approval", "Pengajuan permohonan klaim belum memenuhi persyaratan sehingga tidak dapat diproses lebih lanjut");
        }

        emailSenderService.sendClaimMessage(mail, model);
    }

    public void sendNotifClaimPAR(ClaimPAR claimPAR) {
        TransactionPAR transactionPAR = transactionPARService.getTransactionPARByTransactionId(claimPAR.getTransaction().getId());
        Product product = productService.getProductById(transactionPAR.getProduct().getProductId());
        String statusClaim = transactionPAR.getStatusClaim();
        CustomerPAR customerPAR = customerPARService.getCustomerPARById(transactionPAR.getCustomerPAR().getId());
        String emailCustomer = customerPAR.getEmail();


        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(claimPAR.getEmail());
        mail.setCc(emailCustomer);
        mail.setSubject("[M-ASK] Konfirmasi Klaim Asuransi Property atau Harta Benda");

        Map<String, Object> model = new HashMap<>();
        model.put("name", claimPAR.getName());
        model.put("productName", product.getProductName());

        SimpleDateFormat formatIncident = new SimpleDateFormat("dd MMMM yyyy");
        String incidentDate = formatIncident.format(claimPAR.getIncidentDate());
        model.put("incidentDate", incidentDate);

        String approveValue = String.format("%.0f", claimPAR.getClaimApproval());
        model.put("claimApproval", approveValue);
        model.put("polisNumber", transactionPAR.getTrxparId());
        if (statusClaim.equalsIgnoreCase("disetujui")){
            model.put("approval", "Pengajuan permohonan klaim telah memenuhi persyaratan dan kami setujui dengan jumlah sebesar Rp " + approveValue);
        } else if (statusClaim.equalsIgnoreCase("ditolak")) {
            model.put("approval", "Pengajuan permohonan klaim belum memenuhi persyaratan sehingga tidak dapat diproses lebih lanjut");
        }
        emailSenderService.sendClaimMessage(mail, model);
    }


    public void notifPARenewal(Users users, TransactionPA transactionPA) {
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(transactionPA.getCategoryPA().getCategoryId());
        PackagePA packagePA = packagePAService.getPackagePAById(categoryPA.getPackagePA().getPaId());
        Product product = productService.getProductById(packagePA.getProduct().getProductId());

        SimpleDateFormat formatExp = new SimpleDateFormat("dd MMMM yyyy");
        String expDateString = formatExp.format(transactionPA.getExpDate());

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("[M-ASK] Pemberitahuan Penawaran Pembaharuan Polis");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());
        model.put("productName", product.getProductName());
        model.put("expDate", expDateString);

        emailSenderService.sendRenewalNotified(mail, model);
    }

    public void notifTravelRenewal(Users users, TransactionTravel transactionTravel) {
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(transactionTravel.getPackageTravel().getPtId());
        Product product = productService.getProductById(packageTravel.getProduct().getProductId());

        SimpleDateFormat formatExp = new SimpleDateFormat("dd MMMM yyyy");
        String expDateString = formatExp.format(transactionTravel.getExpDate());

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("[M-ASK] Pemberitahuan Penawaran Pembaharuan Polis");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());
        model.put("productName", product.getProductName());
        model.put("expDate", expDateString);

        emailSenderService.sendRenewalNotified(mail, model);
    }

    public void notifPARRenewal(Users users, TransactionPAR transactionPAR) {
        Product product = productService.getProductById(transactionPAR.getProduct().getProductId());

        SimpleDateFormat formatExp = new SimpleDateFormat("dd MMMM yyyy");
        String expDateString = formatExp.format(transactionPAR.getExpDate());

        Mail mail = new Mail();
        mail.setFrom("mask.askrindo@gmail.com");
        mail.setTo(users.getEmail());
        mail.setSubject("[M-ASK] Pemberitahuan Penawaran Pembaharuan Polis");

        Map<String, Object> model = new HashMap<>();
        model.put("name", users.getName());
        model.put("productName", product.getProductName());
        model.put("expDate", expDateString);

        emailSenderService.sendRenewalNotified(mail, model);
    }
}
