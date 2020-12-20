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
@Table(name = "transaction_travel")
@EntityListeners(AuditingEntityListener.class)
public class TransactionTravel extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_seq")
    @GenericGenerator(
            name = "travel_seq",
            strategy = "com.mask.mask.generator.PrefixedSequenceIdGenerator",
            parameters = {@Parameter(name = PrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TRV-")})
    @Column(name = "trxtravel_id")
    private String id;

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
    @JsonIgnoreProperties("transactionTravel")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "ptId")
    private PackageTravel packageTravel;

    @OneToOne(mappedBy = "transactionTravel", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("transactionTravel")
        private CustomerTravel customerTravel;

    public TransactionTravel(String id, Date startDate, Date expDate, String isPayment, String isClaim, Float premi, String statusPolis, String statusClaim, Float adminFee, String isPromo, Transaction transaction, PackageTravel packageTravel, CustomerTravel customerTravel) {
        this.id = id;
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
        this.packageTravel = packageTravel;
        this.customerTravel = customerTravel;
    }

    public TransactionTravel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public PackageTravel getPackageTravel() {
        return packageTravel;
    }

    public void setPackageTravel(PackageTravel packageTravel) {
        this.packageTravel = packageTravel;
    }

    public CustomerTravel getCustomerTravel() {
        return customerTravel;
    }

    public void setCustomerTravel(CustomerTravel customerTravel) {
        this.customerTravel = customerTravel;
    }

    public String getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(String isPromo) {
        this.isPromo = isPromo;
    }
}
