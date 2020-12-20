package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claim_par")
@EntityListeners(AuditingEntityListener.class)
public class ClaimPAR extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String claimparId;
    private String name;
    private String email;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date reportDate;
    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date incidentDate;

    private String lossCause;
    private String incidentLocation;
    private String furnitureType;
    private String buildingType;
    private String machineType;
    private Float claimSubmission;
    private Float claimApproval;

    private String lossReportName;

    private String authoritiesReport;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

//    @ManyToOne
//    @JoinColumn(name = "transaction_id")
//    private Transaction transaction;


    public ClaimPAR(String claimparId, String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String furnitureType, String buildingType, String machineType, Float claimSubmission, Float claimApproval, String lossReportName, String authoritiesReport, Transaction transaction) {
        this.claimparId = claimparId;
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.furnitureType = furnitureType;
        this.buildingType = buildingType;
        this.machineType = machineType;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.lossReportName = lossReportName;
        this.authoritiesReport = authoritiesReport;
        this.transaction = transaction;
    }

    public ClaimPAR(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String furnitureType, String buildingType, String machineType, Float claimSubmission, Float claimApproval, String lossReportName, String authoritiesReport) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.furnitureType = furnitureType;
        this.buildingType = buildingType;
        this.machineType = machineType;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.lossReportName = lossReportName;
        this.authoritiesReport = authoritiesReport;
    }

    public ClaimPAR(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String furnitureType, String buildingType, String machineType, Float claimSubmission, Float claimApproval, String lossReportName, String authoritiesReport, Transaction transaction) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.furnitureType = furnitureType;
        this.buildingType = buildingType;
        this.machineType = machineType;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.lossReportName = lossReportName;
        this.authoritiesReport = authoritiesReport;
        this.transaction = transaction;
    }

    public ClaimPAR() {
    }

    public String getClaimparId() {
        return claimparId;
    }

    public void setClaimparId(String claimparId) {
        this.claimparId = claimparId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getLossCause() {
        return lossCause;
    }

    public void setLossCause(String lossCause) {
        this.lossCause = lossCause;
    }

    public String getIncidentLocation() {
        return incidentLocation;
    }

    public void setIncidentLocation(String incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String productType) {
        this.furnitureType = productType;
    }

    public Float getClaimSubmission() {
        return claimSubmission;
    }

    public void setClaimSubmission(Float claimSubmission) {
        this.claimSubmission = claimSubmission;
    }

    public Float getClaimApproval() {
        return claimApproval;
    }

    public void setClaimApproval(Float claimApproval) {
        this.claimApproval = claimApproval;
    }

    public String getLossReportName() {
        return lossReportName;
    }

    public void setLossReportName(String fileNameDoc) {
        this.lossReportName = fileNameDoc;
    }


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getAuthoritiesReport() {
        return authoritiesReport;
    }

    public void setAuthoritiesReport(String authoritiesReport) {
        this.authoritiesReport = authoritiesReport;
    }


    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }
}
