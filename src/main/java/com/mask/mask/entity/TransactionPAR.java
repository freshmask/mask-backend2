package com.mask.mask.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mask.mask.generator.PrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction_par")
@EntityListeners(AuditingEntityListener.class)
public class TransactionPAR extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "par_seq")
    @GenericGenerator(
            name = "par_seq",
            strategy = "com.mask.mask.generator.PrefixedSequenceIdGenerator",
            parameters = {@Parameter(name = PrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PAR-")})
    private String trxparId;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date expDate;

    private String isPayment;
    private String isClaim;
    private Float premi;
    private String statusPolis;
    private String statusClaim;
    private Float adminFee;
    private String isPromo;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties("transactionPAR")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne(mappedBy = "transactionPAR", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("transactionPAR")
    private CustomerPAR customerPAR;


    public TransactionPAR(String trxparId, Date startDate, Date expDate, String isPayment, String isClaim, Float premi, String statusPolis, String statusClaim, Float adminFee, String isPromo, Transaction transaction, Product product, CustomerPAR customerPAR) {
        this.trxparId = trxparId;
        this.startDate = startDate;
        this.expDate = expDate;
        this.isPayment = isPayment;
        this.isClaim = isClaim;
        this.premi = premi;
        this.statusPolis = statusPolis;
        this.statusClaim = statusClaim;
        this.adminFee = adminFee;
        this.isPromo = isPromo;
        this.transaction = transaction;
        this.product = product;
        this.customerPAR = customerPAR;
    }

    public TransactionPAR() {
    }

    public String getTrxparId() {
        return trxparId;
    }

    public void setTrxparId(String trxparId) {
        this.trxparId = trxparId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(String isPayment) {
        this.isPayment = isPayment;
    }

    public String getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(String isClaim) {
        this.isClaim = isClaim;
    }


    public String getStatusPolis() {
        return statusPolis;
    }

    public void setStatusPolis(String statusPolis) {
        this.statusPolis = statusPolis;
    }

    public String getStatusClaim() {
        return statusClaim;
    }

    public void setStatusClaim(String statusClaim) {
        this.statusClaim = statusClaim;
    }

    public Float getPremi() {
        return premi;
    }

    public void setPremi(Float premi) {
        this.premi = premi;
    }

    public Float getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(Float adminFee) {
        this.adminFee = adminFee;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerPAR getCustomerPAR() {
        return customerPAR;
    }

    public void setCustomerPAR(CustomerPAR customerPAR) {
        this.customerPAR = customerPAR;
    }

    public String getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(String isPromo) {
        this.isPromo = isPromo;
    }
}
