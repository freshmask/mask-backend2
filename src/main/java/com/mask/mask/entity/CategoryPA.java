package com.mask.mask.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "category_pa")
@EntityListeners(AuditingEntityListener.class)
public class CategoryPA extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String categoryId;
    private Integer days;
    private Float price;
    private Float pricePromo;
    private String voucher;
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "pa_id")
    private PackagePA packagePA;

    public CategoryPA(String categoryId, Integer days, Float price, Float pricePromo, String voucher) {
        this.categoryId = categoryId;
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
    }

    public CategoryPA(Integer days, Float price, Float pricePromo, String voucher) {
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
    }

    public CategoryPA(String categoryId, Integer days, Float price, Float pricePromo, String voucher, PackagePA packagePA) {
        this.categoryId = categoryId;
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
        this.packagePA = packagePA;
    }

    public CategoryPA(String categoryId, Integer days, Float price, Float pricePromo, String voucher, String isActive) {
        this.categoryId = categoryId;
        this.days = days;
        this.price = price;
        this.pricePromo = pricePromo;
        this.voucher = voucher;
        this.isActive = isActive;
    }

    public CategoryPA() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public PackagePA getPackagePA() {
        return packagePA;
    }

    public void setPackagePA(PackagePA packagePA) {
        this.packagePA = packagePA;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
