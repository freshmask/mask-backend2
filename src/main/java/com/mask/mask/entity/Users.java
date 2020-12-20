package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_user")
@EntityListeners(AuditingEntityListener.class)
public class Users extends Auditable<String>{

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Id
    @Column(name = "user_id")
    private String id;

    private String name;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String idCardNo;
    private String password;

    private Integer version;

    @JsonFormat(timezone = "Asia/Jakarta",pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String gender;
    private String role;
    private String isActive;

    @Column(unique=true)
    private String phoneNumber;
    private String fingerData;

    private String fileName;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();

    @Transient
    private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();

    public Users(String id, String name, String email, String idCardNo, String password, Integer version, Date birthdate, String gender, String role, String isActive, String phoneNumber, String fingerData, String fileName, List<Transaction> transactionList, Collection<GrantedAuthority> listOfgrantedAuthorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idCardNo = idCardNo;
        this.password = password;
        this.version = version;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.fingerData = fingerData;
        this.fileName = fileName;
        this.transactionList = transactionList;
        this.listOfgrantedAuthorities = listOfgrantedAuthorities;
    }

    public Users(String fileName) {
        this.fileName = fileName;
    }

    public Users() {
    }

    public Users(String name, String email, String idCardNo, String password, Integer version, Date birthdate, String gender, String role, String isActive, String phoneNumber, String fingerData, String fileName) {
        this.name = name;
        this.email = email;
        this.idCardNo = idCardNo;
        this.password = password;
        this.version = version;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.fingerData = fingerData;
        this.fileName = fileName;
    }

    public Users(String name, String email, String idCardNo, String password, Integer version, Date birthdate, String gender, String role, String isActive, String phoneNumber, String fingerData, String fileName, List<Transaction> transactionList, Collection<GrantedAuthority> listOfgrantedAuthorities) {
        this.name = name;
        this.email = email;
        this.idCardNo = idCardNo;
        this.password = password;
        this.version = version;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.fingerData = fingerData;
        this.fileName = fileName;
        this.transactionList = transactionList;
        this.listOfgrantedAuthorities = listOfgrantedAuthorities;
    }

    public Users(String name, String email, String idCardNo, String password, Date birthdate, String gender, String role, String isActive, String phoneNumber, String fingerData, String fileName) {
        this.name = name;
        this.email = email;
        this.idCardNo = idCardNo;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.fingerData = fingerData;
        this.fileName = fileName;
    }

    public Users(String name, String email, String idCardNo, String password, Date birthdate, String gender, String role, String isActive, String phoneNumber, String fingerData) {
        this.name = name;
        this.email = email;
        this.idCardNo = idCardNo;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.fingerData = fingerData;
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

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String username) {
        this.idCardNo = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFingerData() {
        return fingerData;
    }

    public void setFingerData(String fingerData) {
        this.fingerData = fingerData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public Collection<GrantedAuthority> getListOfgrantedAuthorities() {
        return listOfgrantedAuthorities;
    }

    public void setListOfgrantedAuthorities(Collection<GrantedAuthority> listOfgrantedAuthorities) {
        this.listOfgrantedAuthorities = listOfgrantedAuthorities;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
