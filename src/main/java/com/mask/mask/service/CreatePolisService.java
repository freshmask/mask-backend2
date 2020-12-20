package com.mask.mask.service;

import com.itextpdf.text.DocumentException;
import com.mask.mask.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CreatePolisService {

    @Value("${document-policy}")
    String documentPolicy;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    CategoryPAService categoryPAService;

    @Autowired
    PackageTravelService packageTravelService;

    @Autowired
    ProductService productService;

    private String xhtmlConvert(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }

    public void createPolisPA(TransactionPA transactionPA, CustomerPA customerPA) {
        CategoryPA categoryPA = categoryPAService.getCategoryPAById(transactionPA.getCategoryPA().getCategoryId());
        Context context = new Context();

        context.setVariable("name", customerPA.getName());
        context.setVariable("identityNumber", customerPA.getIdentityNo());
        context.setVariable("phoneNumber", customerPA.getPhoneNumber());
        context.setVariable("polisNumber", transactionPA.getTrxpaId());
        context.setVariable("email", customerPA.getEmail());
        context.setVariable("heirName", customerPA.getHeirName());
        context.setVariable("heirPhoneNumber", customerPA.getPhoneNumber());
        context.setVariable("emailHeir", customerPA.getHeirEmail());
        context.setVariable("packageName", categoryPA.getPackagePA().getName());
        context.setVariable("days", categoryPA.getDays());

        SimpleDateFormat formatStart = new SimpleDateFormat("dd MMMM yyyy");
        String startDateString = formatStart.format(transactionPA.getStartDate());
        context.setVariable("startPeriode", startDateString);
        SimpleDateFormat formatEnd = new SimpleDateFormat("dd MMMM yyyy");
        String endDateString = formatEnd.format(transactionPA.getExpDate());
        context.setVariable("endPeriode", endDateString);

        String compensation = String.format("%.0f", categoryPA.getPackagePA().getCompensation());

        context.setVariable("compensation", compensation);
        context.setVariable("premi", transactionPA.getPremi());
        context.setVariable("benefits", "TERLAMPIR");

        SimpleDateFormat formatCurrent = new SimpleDateFormat("dd MMMM yyyy");
        String currentDateString = formatCurrent.format(new Date());
        context.setVariable("date", currentDateString);

        String generatePolisNumber = transactionPA.getTrxpaId();

        String htmlContentToRender = templateEngine.process("polisPA", context);
        String xHtml = null;
        try {
            xHtml = xhtmlConvert(htmlContentToRender);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = null;
        try {
            baseUrl = FileSystems
                    .getDefault()
                    .getPath("src", "main", "resources","templates")
                    .toUri()
                    .toURL()
                    .toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(documentPolicy + generatePolisNumber +".pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return details;

    }

    public void createPolisTravel(TransactionTravel transactionTravel, CustomerTravel customerTravel) {
        PackageTravel packageTravel = packageTravelService.getPackageTravelById(transactionTravel.getPackageTravel().getPtId());
        Context context = new Context();

        context.setVariable("customerName", customerTravel.getName());
        context.setVariable("address", customerTravel.getAddress());
        context.setVariable("phoneNumber", customerTravel.getPhoneNumber());
        context.setVariable("identityNo", customerTravel.getIdentityNo());
        context.setVariable("travelKind", customerTravel.getTravelKind());
        context.setVariable("departureCity", customerTravel.getDepartureCity());
        context.setVariable("destinationCity", customerTravel.getDestinationCity());

        SimpleDateFormat formatStart = new SimpleDateFormat("dd MMMM yyyy");
        String startDateString = formatStart.format(transactionTravel.getStartDate());
        context.setVariable("startDate", startDateString);
        SimpleDateFormat formatEnd = new SimpleDateFormat("dd MMMM yyyy");
        String endDateString = formatEnd.format(transactionTravel.getExpDate());
        context.setVariable("endDate", endDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(customerTravel.getDepartureDate());
        Integer journeylength = Integer.parseInt(customerTravel.getJourneyLength());
        calendar.add(Calendar.DAY_OF_MONTH, journeylength);
        SimpleDateFormat formatj = new SimpleDateFormat("dd MMMM yyyy");
        String journeyl = formatj.format(calendar.getTime());
        context.setVariable("journeyLength", journeyl);
        calendar.add(Calendar.DAY_OF_MONTH, -journeylength);

        context.setVariable("purpose", customerTravel.getDeparturePurpose());
        context.setVariable("heirName", customerTravel.getHeirName());
        context.setVariable("heirPhone", customerTravel.getHeirPhoneNumber());
        context.setVariable("heirEmail", customerTravel.getHeirEmail());

        SimpleDateFormat formatDate = new SimpleDateFormat("dd MMMM yyyy");
        String currentDateString = formatDate.format(new Date());
        context.setVariable("printDate", currentDateString);

        context.setVariable("package", packageTravel.getName());
        context.setVariable("premi", transactionTravel.getPremi());
        context.setVariable("polisNumber", transactionTravel.getId());

        String htmlContentToRender = templateEngine.process("polisTravel", context);
        String xHtml = null;
        try {
            xHtml = xhtmlConvert(htmlContentToRender);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = null;
        try {
            baseUrl = FileSystems
                    .getDefault()
                    .getPath("src", "main", "resources","templates")
                    .toUri()
                    .toURL()
                    .toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(documentPolicy + transactionTravel.getId() +".pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return details;

    }

    public void createPolisPAR(TransactionPAR transactionPAR, CustomerPAR customerPAR) {
        Product product = productService.getProductById(transactionPAR.getProduct().getProductId());
        Context context = new Context();

        context.setVariable("polisNumber", transactionPAR.getTrxparId());
        context.setVariable("guaranteeType", "Polis Standar Asuransi Kebakaran Indonesia");
        context.setVariable("customerName", customerPAR.getName());
        context.setVariable("address", customerPAR.getAddress());

        SimpleDateFormat formatStart = new SimpleDateFormat("dd MMMM yyyy");
        String startDateString = formatStart.format(transactionPAR.getStartDate());
        context.setVariable("startDate", startDateString);
        SimpleDateFormat formatEnd = new SimpleDateFormat("dd MMMM yyyy");
        String endDateString = formatEnd.format(transactionPAR.getExpDate());
        context.setVariable("endDate", endDateString);

        context.setVariable("riskLocation", customerPAR.getRiskLocation());
        context.setVariable("occupation", customerPAR.getOccupation());
        context.setVariable("construction", customerPAR.getConstructionClass());

        String sumValue = String.format("%.0f", customerPAR.getBuildingCoverageValue()+customerPAR.getFurnitureCoverageValue()+customerPAR.getMachineCoverageValue());
        context.setVariable("totalCoverage", sumValue);
        context.setVariable("principalGuarantee", "Kebakaran, Sambaran Petir, Ledakan, Kejatuhan Pesawat Terbang, dan Asap");
        if(customerPAR.getMachineCoverageValue()!=0f){
            String machValue = String.format("%.0f", customerPAR.getMachineCoverageValue());
            context.setVariable("machineCoverage", machValue );
        } else {
            context.setVariable("machineCoverage", "-");
        }

        if(customerPAR.getBuildingCoverageValue()!=0f){
            String buildValue = String.format("%.0f", customerPAR.getBuildingCoverageValue());
            context.setVariable("buildingCoverage", buildValue);
        } else {
            context.setVariable("buildingCoverage", "-");
        }

        if(customerPAR.getFurnitureCoverageValue()!=0f){
            String furnValue = String.format("%.0f", customerPAR.getFurnitureCoverageValue());
            context.setVariable("furnitureCoverage", furnValue);
        } else {
            context.setVariable("furnitureCoverage", "-");
        }

        context.setVariable("selfRisk", "FLEXAS: nill");
        context.setVariable("clause", "Terlampir");

        if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("1")){
            context.setVariable("rate", "0.0294% per tahun");
        } else if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("2")){
            context.setVariable("rate", "0.0397% per tahun");
        } else if (customerPAR.getOccupation().equalsIgnoreCase("2976") && customerPAR.getConstructionClass().equalsIgnoreCase("3")){
            context.setVariable("rate", "0.0499% per tahun");
        } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("1")){
            context.setVariable("rate", "0.0478% per tahun");
        } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("2")){
            context.setVariable("rate", "0.0645% per tahun");
        } else if (customerPAR.getOccupation().equalsIgnoreCase("29761") && customerPAR.getConstructionClass().equalsIgnoreCase("3")){
            context.setVariable("rate", "0.0812% per tahun");
        }

        float total = transactionPAR.getPremi() + transactionPAR.getAdminFee() + 100000;

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String dateString = format.format(new Date());

        context.setVariable("polisFee", 100000);
        context.setVariable("printDate", dateString);

        context.setVariable("premiPAR", transactionPAR.getPremi());
        context.setVariable("adminFee", transactionPAR.getAdminFee());
        context.setVariable("total", total);

        String htmlContentToRender = templateEngine.process("polisPAR", context);
        String xHtml = null;
        try {
            xHtml = xhtmlConvert(htmlContentToRender);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ITextRenderer renderer = new ITextRenderer();

        String baseUrl = null;
        try {
            baseUrl = FileSystems
                    .getDefault()
                    .getPath("src", "main", "resources","templates")
                    .toUri()
                    .toURL()
                    .toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(documentPolicy + transactionPAR.getTrxparId() +".pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String trialxhtmlConvert(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }
}
