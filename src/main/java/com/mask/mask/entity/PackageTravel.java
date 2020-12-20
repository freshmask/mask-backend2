package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "package_travel")
@EntityListeners(AuditingEntityListener.class)
public class PackageTravel extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String ptId;
    private String name;
    private Integer days;
    private Float price;
    private Float pricePromo;
    private String voucher;
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public PackageTravel(String ptId, String name, Integer days, Float price, Float pricePromo, String voucher, Product product) {
        this.ptId = ptId;
        this.name = name;
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
        this.product = product;
    }

    public PackageTravel(String ptId, String name, Integer days, Float price, Float pricePromo, String voucher, String isActive) {
        this.ptId = ptId;
        this.name = name;
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
        this.isActive = isActive;
    }

    public PackageTravel() {
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPricePromo() {
        return pricePromo;
    }

    public void setPricePromo(Float pricePromo) {
        this.pricePromo = pricePromo;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
