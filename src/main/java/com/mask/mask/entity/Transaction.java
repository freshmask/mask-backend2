package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction extends Auditable<String>{

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Id
    @Column(name = "transaction_id")
    private String id;

    private Integer version;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(mappedBy = "transaction")
    @JsonIgnore
    private ClaimPA claimPA;

    @OneToOne(mappedBy = "transaction")
    @JsonIgnore
    private ClaimPAR claimPAR;

    @OneToOne(mappedBy = "transaction")
    @JsonIgnore
    private ClaimTravel claimTravel;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private TransactionPA transactionPA;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private TransactionTravel transactionTravel;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private TransactionPAR transactionPAR;


    public Transaction(String id, Users users, ClaimPA claimPA, ClaimPAR claimPAR, ClaimTravel claimTravel, TransactionPA transactionPA, TransactionTravel transactionTravel, TransactionPAR transactionPAR) {
        this.id = id;
        this.users = users;
        this.claimPA = claimPA;
        this.claimPAR = claimPAR;
        this.claimTravel = claimTravel;
        this.transactionPA = transactionPA;
        this.transactionTravel = transactionTravel;
        this.transactionPAR = transactionPAR;
    }

    public Transaction() {
    }

    public Transaction(String id, Integer version, Users users, ClaimPA claimPA, ClaimPAR claimPAR, ClaimTravel claimTravel, TransactionPA transactionPA, TransactionTravel transactionTravel, TransactionPAR transactionPAR) {
        this.id = id;
        this.version = version;
        this.users = users;
        this.claimPA = claimPA;
        this.claimPAR = claimPAR;
        this.claimTravel = claimTravel;
        this.transactionPA = transactionPA;
        this.transactionTravel = transactionTravel;
        this.transactionPAR = transactionPAR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ClaimPA getClaimPA() {
        return claimPA;
    }

    public void setClaimPA(ClaimPA claimPA) {
        this.claimPA = claimPA;
    }

    public ClaimPAR getClaimPAR() {
        return claimPAR;
    }

    public void setClaimPAR(ClaimPAR claimPAR) {
        this.claimPAR = claimPAR;
    }

    public ClaimTravel getClaimTravel() {
        return claimTravel;
    }

    public void setClaimTravel(ClaimTravel claimTravel) {
        this.claimTravel = claimTravel;
    }

    public TransactionPA getTransactionPA() {
        return transactionPA;
    }

    public void setTransactionPA(TransactionPA transactionPA) {
        this.transactionPA = transactionPA;
    }

    public TransactionTravel getTransactionTravel() {
        return transactionTravel;
    }

    public void setTransactionTravel(TransactionTravel transactionTravel) {
        this.transactionTravel = transactionTravel;
    }

    public TransactionPAR getTransactionPAR() {
        return transactionPAR;
    }

    public void setTransactionPAR(TransactionPAR transactionPAR) {
        this.transactionPAR = transactionPAR;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
