package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "customer_par")
@EntityListeners(AuditingEntityListener.class)
public class CustomerPAR extends Auditable<String> {

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Id
    @Column(name = "customerpar_id")
    private String id;

    private String name;
    private String identityType;
    private String identityNo;
    private String phoneNumber;
    private String email;
    private Integer timePeriod;
    private String riskLocation;
    private String occupation;
    private String constructionClass;
    private String buildingYear;
    private String floorNumber;
    private String sprinkle;
    private String buildingType;
    private Float buildingCoverageValue;
    private String machineType;
    private Float machineCoverageValue;
    private String furnitureType;
    private Float furnitureCoverageValue;
    private String pump;
    private String fireAlarm;
    private String address;
    private String documentName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trxparId")
    private TransactionPAR transactionPAR;

    public CustomerPAR(String id, String name, String identityType, String identityNo, String phoneNumber, String email, Integer timePeriod, String riskLocation, String occupation, String constructionClass, String buildingYear, String floorNumber, String sprinkle, String buildingType, Float buildingCoverageValue, String machineType, Float machineCoverageValue, String furnitureType, Float furnitureCoverageValue, String pump, String fireAlarm, String address, String documentName, TransactionPAR transactionPAR) {
        this.id = id;
        this.name = name;
        this.identityType = identityType;
        this.identityNo = identityNo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timePeriod = timePeriod;
        this.riskLocation = riskLocation;
        this.occupation = occupation;
        this.constructionClass = constructionClass;
        this.buildingYear = buildingYear;
        this.floorNumber = floorNumber;
        this.sprinkle = sprinkle;
        this.buildingType = buildingType;
        this.buildingCoverageValue = buildingCoverageValue;
        this.machineType = machineType;
        this.machineCoverageValue = machineCoverageValue;
        this.furnitureType = furnitureType;
        this.furnitureCoverageValue = furnitureCoverageValue;
        this.pump = pump;
        this.fireAlarm = fireAlarm;
        this.address = address;
        this.documentName = documentName;
        this.transactionPAR = transactionPAR;
    }

    public CustomerPAR() {
    }

    public CustomerPAR(String name, String identityType, String identityNo, String phoneNumber, String email, Integer timePeriod, String riskLocation, String occupation, String constructionClass, String buildingYear, String floorNumber, String sprinkle, String buildingType, Float buildingCoverageValue, String machineType, Float machineCoverageValue, String furnitureType, Float furnitureCoverageValue, String pump, String fireAlarm, String address, String documentName) {
        this.name = name;
        this.identityType = identityType;
        this.identityNo = identityNo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timePeriod = timePeriod;
        this.riskLocation = riskLocation;
        this.occupation = occupation;
        this.constructionClass = constructionClass;
        this.buildingYear = buildingYear;
        this.floorNumber = floorNumber;
        this.sprinkle = sprinkle;
        this.buildingType = buildingType;
        this.buildingCoverageValue = buildingCoverageValue;
        this.machineType = machineType;
        this.machineCoverageValue = machineCoverageValue;
        this.furnitureType = furnitureType;
        this.furnitureCoverageValue = furnitureCoverageValue;
        this.pump = pump;
        this.fireAlarm = fireAlarm;
        this.address = address;
        this.documentName = documentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Integer timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getRiskLocation() {
        return riskLocation;
    }

    public void setRiskLocation(String riskLocation) {
        this.riskLocation = riskLocation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getConstructionClass() {
        return constructionClass;
    }

    public void setConstructionClass(String constructionClass) {
        this.constructionClass = constructionClass;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String type) {
        this.buildingType = type;
    }

    public String getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(String buildingYear) {
        this.buildingYear = buildingYear;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getSprinkle() {
        return sprinkle;
    }

    public void setSprinkle(String sprinkle) {
        this.sprinkle = sprinkle;
    }


    public String getPump() {
        return pump;
    }

    public void setPump(String pump) {
        this.pump = pump;
    }

    public String getFireAlarm() {
        return fireAlarm;
    }

    public void setFireAlarm(String fireAlarm) {
        this.fireAlarm = fireAlarm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public TransactionPAR getTransactionPAR() {
        return transactionPAR;
    }

    public void setTransactionPAR(TransactionPAR transactionPAR) {
        this.transactionPAR = transactionPAR;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }


    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public Float getBuildingCoverageValue() {
        return buildingCoverageValue;
    }

    public void setBuildingCoverageValue(Float buildingCoverageValue) {
        this.buildingCoverageValue = buildingCoverageValue;
    }

    public Float getMachineCoverageValue() {
        return machineCoverageValue;
    }

    public void setMachineCoverageValue(Float machineCoverageValue) {
        this.machineCoverageValue = machineCoverageValue;
    }

    public Float getFurnitureCoverageValue() {
        return furnitureCoverageValue;
    }

    public void setFurnitureCoverageValue(Float furnitureCoverageValue) {
        this.furnitureCoverageValue = furnitureCoverageValue;
    }
}
