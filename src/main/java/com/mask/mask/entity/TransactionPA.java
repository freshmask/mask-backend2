package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mask.mask.generator.PrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "transaction_pa")
@EntityListeners(AuditingEntityListener.class)
public class TransactionPA extends Auditable<String> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pa_seq")
    @GenericGenerator(
            name = "pa_seq",
            strategy = "com.mask.mask.generator.PrefixedSequenceIdGenerator",
            parameters = {@Parameter(name = PrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PA-")})
    private String trxpaId;

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
    @JsonIgnoreProperties("transactionPA")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryPA categoryPA;

    @OneToOne(mappedBy = "transactionPA", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("transactionPA")
    private CustomerPA customerPA;

    public TransactionPA(String trxpaId, Date startDate, Date expDate, String isPayment, String isClaim, Float premi, String statusPolis, String statusClaim, Float adminFee, String isPromo, Transaction transaction, CategoryPA categoryPA, CustomerPA customerPA) {
        this.trxpaId = trxpaId;
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
        this.categoryPA = categoryPA;
        this.customerPA = customerPA;
    }

    public TransactionPA() {
    }

    public String getTrxpaId() {
        return trxpaId;
    }

    public void setTrxpaId(String trxpaId) {
        this.trxpaId = trxpaId;
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

    public CategoryPA getCategoryPA() {
        return categoryPA;
    }

    public void setCategoryPA(CategoryPA categoryPA) {
        this.categoryPA = categoryPA;
    }

    public CustomerPA getCustomerPA() {
        return customerPA;
    }

    public void setCustomerPA(CustomerPA customerPA) {
        this.customerPA = customerPA;
    }

    public String getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(String isPromo) {
        this.isPromo = isPromo;
    }
}
