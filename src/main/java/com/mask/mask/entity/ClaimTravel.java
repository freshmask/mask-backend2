package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claim_travel")
@EntityListeners(AuditingEntityListener.class)
public class ClaimTravel extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "claimtravel_id")
    private String id;

    private String name;
    private String email;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date reportDate;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date incidentDate;
    private String lossCause;
    private String incidentLocation;
    private String medicalCertificate;
    private String medicalExpenses;
    private String deathCertificate;
    private Float claimSubmission;
    private Float claimApproval;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public ClaimTravel(String id, String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String medicalCertificate, String medicalExpenses, String deathCertificate, Float claimSubmission, Float claimApproval, Transaction transaction) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.medicalCertificate = medicalCertificate;
        this.medicalExpenses = medicalExpenses;
        this.deathCertificate = deathCertificate;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.transaction = transaction;
    }

    public ClaimTravel() {
    }

    public ClaimTravel(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String medicalCertificate, String medicalExpenses, String deathCertificate, Float claimSubmission, Float claimApproval, Transaction transaction) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.medicalCertificate = medicalCertificate;
        this.medicalExpenses = medicalExpenses;
        this.deathCertificate = deathCertificate;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.transaction = transaction;
    }

    public ClaimTravel(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String medicalCertificate, String medicalExpenses, String deathCertificate, Float claimSubmission, Float claimApproval) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.medicalCertificate = medicalCertificate;
        this.medicalExpenses = medicalExpenses;
        this.deathCertificate = deathCertificate;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
    }

    public ClaimTravel(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String medicalCertificate, String medicalExpenses, Float claimSubmission, Float claimApproval, Transaction transaction) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.medicalCertificate = medicalCertificate;
        this.medicalExpenses = medicalExpenses;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.transaction = transaction;
    }

    public ClaimTravel(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, String medicalCertificate, String medicalExpenses, Float claimSubmission, Float claimApproval) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.medicalCertificate = medicalCertificate;
        this.medicalExpenses = medicalExpenses;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
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

    public String getMedicalCertificate() {
        return medicalCertificate;
    }

    public void setMedicalCertificate(String documentName) {
        this.medicalCertificate = documentName;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getMedicalExpenses() {
        return medicalExpenses;
    }

    public void setMedicalExpenses(String medicalExpenses) {
        this.medicalExpenses = medicalExpenses;
    }

    public String getDeathCertificate() {
        return deathCertificate;
    }

    public void setDeathCertificate(String deathCertificate) {
        this.deathCertificate = deathCertificate;
    }

}
