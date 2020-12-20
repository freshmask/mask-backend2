package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_travel")
@EntityListeners(AuditingEntityListener.class)
public class CustomerTravel extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String customertravelId;
    private String name;
    private String address;
    private String identityType;
    private String identityNo;
    private String phoneNumber;
    private String email;
    private String heirName;
    private String heirPhoneNumber;
    private String relationship;
    private String heirEmail;
    private String travelKind;
    private String departureCity;
    private String destinationCity;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date departureDate;
    private String journeyLength;
    private String departurePurpose;

    private String fileNamePhoto;
    private String fileNameIdentity;

    private String voucherTravel;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trxtravelId")
    private TransactionTravel transactionTravel;

    public CustomerTravel(String customertravelId, String name, String address, String identityType, String identityNo, String phoneNumber, String email, String heirName, String heirPhoneNumber, String relationship, String heirEmail, String travelKind, String departureCity, String destinationCity, Date departureDate, String journeyLength, String departurePurpose, String fileNamePhoto, String fileNameIdentity, String voucherTravel, TransactionTravel transactionTravel) {
        this.customertravelId = customertravelId;
        this.name = name;
        this.address = address;
        this.identityType = identityType;
        this.identityNo = identityNo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.heirName = heirName;
        this.heirPhoneNumber = heirPhoneNumber;
        this.relationship = relationship;
        this.heirEmail = heirEmail;
        this.travelKind = travelKind;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.journeyLength = journeyLength;
        this.departurePurpose = departurePurpose;
        this.fileNamePhoto = fileNamePhoto;
        this.fileNameIdentity = fileNameIdentity;
        this.voucherTravel = voucherTravel;
        this.transactionTravel = transactionTravel;
    }

    public CustomerTravel(String name, String address, String identityType, String identityNo, String phoneNumber, String email, String heirName, String heirPhoneNumber, String relationship, String heirEmail, String travelKind, String departureCity, String destinationCity, Date departureDate, String journeyLength, String departurePurpose, String fileNamePhoto, String fileNameIdentity, String voucherTravel) {
        this.name = name;
        this.address = address;
        this.identityType = identityType;
        this.identityNo = identityNo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.heirName = heirName;
        this.heirPhoneNumber = heirPhoneNumber;
        this.relationship = relationship;
        this.heirEmail = heirEmail;
        this.travelKind = travelKind;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.journeyLength = journeyLength;
        this.departurePurpose = departurePurpose;
        this.fileNamePhoto = fileNamePhoto;
        this.fileNameIdentity = fileNameIdentity;
        this.voucherTravel = voucherTravel;
    }

    public CustomerTravel() {
    }

    public String getCustomertravelId() {
        return customertravelId;
    }

    public void setCustomertravelId(String customertravelId) {
        this.customertravelId = customertravelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
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

    public String getHeirName() {
        return heirName;
    }

    public void setHeirName(String heirName) {
        this.heirName = heirName;
    }

    public String getHeirPhoneNumber() {
        return heirPhoneNumber;
    }

    public void setHeirPhoneNumber(String heirPhoneNumber) {
        this.heirPhoneNumber = heirPhoneNumber;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getHeirEmail() {
        return heirEmail;
    }

    public void setHeirEmail(String heirEmail) {
        this.heirEmail = heirEmail;
    }

    public String getTravelKind() {
        return travelKind;
    }

    public void setTravelKind(String travelKind) {
        this.travelKind = travelKind;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getJourneyLength() {
        return journeyLength;
    }

    public void setJourneyLength(String journeyLength) {
        this.journeyLength = journeyLength;
    }

    public String getDeparturePurpose() {
        return departurePurpose;
    }

    public void setDeparturePurpose(String departurePurpose) {
        this.departurePurpose = departurePurpose;
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

    public TransactionTravel getTransactionTravel() {
        return transactionTravel;
    }

    public void setTransactionTravel(TransactionTravel transactionTravel) {
        this.transactionTravel = transactionTravel;
    }

    public String getVoucherTravel() {
        return voucherTravel;
    }

    public void setVoucherTravel(String voucherTravel) {
        this.voucherTravel = voucherTravel;
    }

}
