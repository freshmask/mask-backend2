package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String productId;
    private String productName;
    private String isActive;
    private Float discount;

    private Integer version;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PackagePA> packagePAList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PackageTravel> packageTravelList = new ArrayList<>();

    public Product(String productId, String productName, String isActive, Float discount, List<PackagePA> packagePAList, List<PackageTravel> packageTravelList) {
        this.productId = productId;
        this.productName = productName;
        this.isActive = isActive;
        this.discount = discount;
        this.packagePAList = packagePAList;
        this.packageTravelList = packageTravelList;
    }

    public Product() {
    }

    public Product(String productId, String productName, String isActive, Float discount, Integer version, List<PackagePA> packagePAList, List<PackageTravel> packageTravelList) {
        this.productId = productId;
        this.productName = productName;
        this.isActive = isActive;
        this.discount = discount;
        this.version = version;
        this.packagePAList = packagePAList;
        this.packageTravelList = packageTravelList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public List<PackagePA> getPackagePAList() {
        return packagePAList;
    }

    public void setPackagePAList(List<PackagePA> packagePAList) {
        this.packagePAList = packagePAList;
    }

    public List<PackageTravel> getPackageTravelList() {
        return packageTravelList;
    }

    public void setPackageTravelList(List<PackageTravel> packageTravelList) {
        this.packageTravelList = packageTravelList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
