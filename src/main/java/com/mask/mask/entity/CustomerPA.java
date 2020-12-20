package com.mask.mask.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "customer_pa")
@EntityListeners(AuditingEntityListener.class)
public class CustomerPA extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String customerpaId;
    private String name;
    private String identityNo;
    private String identityType;
    private String phoneNumber;
    private String email;
    private String address;
    private String heirName;
    private String relationship;
    private String heirPhoneNumber;
    private String heirEmail;
    private String fileNamePhoto;
    private String fileNameIdentity;
    private String voucherPA;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trxpaId")
    private TransactionPA transactionPA;

//    @ManyToOne
//    @JoinColumn(name = "trxpaId")
//    private TransactionPA transactionPA;

//    @OneToOne
//    @JoinColumn(name = "trxpa_id")
//    private TransactionPA transactionPA;


    public CustomerPA(String customerpaId, String name, String identityNo, String identityType, String phoneNumber, String email, String address, String heirName, String relationship, String heirPhoneNumber, String heirEmail, String fileNamePhoto, String fileNameIdentity, String voucherPA, TransactionPA transactionPA) {
        this.customerpaId = customerpaId;
        this.name = name;
        this.identityNo = identityNo;
        this.identityType = identityType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.heirName = heirName;
        this.relationship = relationship;
        this.heirPhoneNumber = heirPhoneNumber;
        this.heirEmail = heirEmail;
        this.fileNamePhoto = fileNamePhoto;
        this.fileNameIdentity = fileNameIdentity;
        this.voucherPA = voucherPA;
        this.transactionPA = transactionPA;
    }

    public CustomerPA() {
    }



    public CustomerPA(String name, String identityNo, String identityType, String phoneNumber, String email, String address, String heirName, String relationship, String heirPhoneNumber, String heirEmail, String fileNamePhoto, String fileNameIdentity, String voucherPA) {
        this.name = name;
        this.identityNo = identityNo;
        this.identityType = identityType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.heirName = heirName;
        this.relationship = relationship;
        this.heirPhoneNumber = heirPhoneNumber;
        this.heirEmail = heirEmail;
        this.fileNamePhoto = fileNamePhoto;
        this.fileNameIdentity = fileNameIdentity;
        this.voucherPA = voucherPA;
    }

    public String getVoucherPA() {
        return voucherPA;
    }

    public void setVoucherPA(String voucherPA) {
        this.voucherPA = voucherPA;
    }

    public String getCustomerpaId() {
        return customerpaId;
    }

    public void setCustomerpaId(String customerpaId) {
        this.customerpaId = customerpaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeirName() {
        return heirName;
    }

    public void setHeirName(String heirName) {
        this.heirName = heirName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getHeirPhoneNumber() {
        return heirPhoneNumber;
    }

    public void setHeirPhoneNumber(String heirPhoneNumber) {
        this.heirPhoneNumber = heirPhoneNumber;
    }

    public String getHeirEmail() {
        return heirEmail;
    }

    public void setHeirEmail(String heirEmail) {
        this.heirEmail = heirEmail;
    }

    public String getFileNamePhoto() {
        return fileNamePhoto;
    }

    public void setFileNamePhoto(String fileNamePhoto) {
        this.fileNamePhoto = fileNamePhoto;
    }


    public String getFileNameIdentity() {
        return fileNameIdentity;
    }

    public void setFileNameIdentity(String fileNameIdentity) {
        this.fileNameIdentity = fileNameIdentity;
    }


    public TransactionPA getTransactionPA() {
        return transactionPA;
    }

    public void setTransactionPA(TransactionPA transactionPA) {
        this.transactionPA = transactionPA;
    }
}
