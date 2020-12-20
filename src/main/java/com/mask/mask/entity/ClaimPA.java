package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claim_pa")
@EntityListeners(AuditingEntityListener.class)
public class ClaimPA extends Auditable<String> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "claimpa_id")
    private String id;

    private String name;
    private String email;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date reportDate;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date incidentDate;
    private String lossCause;
    private String incidentLocation;
    private Float claimSubmission;
    private Float claimApproval;

    private String medicalCertificateName;

    private String medicalExpensesName;

    private String deathCertificateName;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public ClaimPA(String id, String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, Float claimSubmission, Float claimApproval, String medicalCertificateName, String medicalExpensesName, String deathCertificateName, Transaction transaction) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.medicalCertificateName = medicalCertificateName;
        this.medicalExpensesName = medicalExpensesName;
        this.deathCertificateName = deathCertificateName;
        this.transaction = transaction;
    }

    public ClaimPA(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, Float claimSubmission, Float claimApproval, String medicalCertificateName, String medicalExpensesName, String deathCertificateName) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.medicalCertificateName = medicalCertificateName;
        this.medicalExpensesName = medicalExpensesName;
        this.deathCertificateName = deathCertificateName;
    }

    public ClaimPA() {
    }

    public ClaimPA(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, Float claimSubmission, Float claimApproval, String medicalCertificateName, String medicalExpensesName, String deathCertificateName, Transaction transaction) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.medicalCertificateName = medicalCertificateName;
        this.medicalExpensesName = medicalExpensesName;
        this.deathCertificateName = deathCertificateName;
        this.transaction = transaction;
    }

    public ClaimPA(String name, String email, Date reportDate, Date incidentDate, String lossCause, String incidentLocation, Float claimSubmission, Float claimApproval, String medicalCertificateName, String medicalExpensesName, Transaction transaction) {
        this.name = name;
        this.email = email;
        this.reportDate = reportDate;
        this.incidentDate = incidentDate;
        this.lossCause = lossCause;
        this.incidentLocation = incidentLocation;
        this.claimSubmission = claimSubmission;
        this.claimApproval = claimApproval;
        this.medicalCertificateName = medicalCertificateName;
        this.medicalExpensesName = medicalExpensesName;
        this.transaction = transaction;
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

    public String getMedicalCertificateName() {
        return medicalCertificateName;
    }

    public void setMedicalCertificateName(String medicalCertificateName) {
        this.medicalCertificateName = medicalCertificateName;
    }

    public String getMedicalExpensesName() {
        return medicalExpensesName;
    }

    public void setMedicalExpensesName(String medicalExpensesName) {
        this.medicalExpensesName = medicalExpensesName;
    }


    public String getDeathCertificateName() {
        return deathCertificateName;
    }

    public void setDeathCertificateName(String deathCertificateName) {
        this.deathCertificateName = deathCertificateName;
    }


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
