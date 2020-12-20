package com.mask.mask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "package_pa")
@EntityListeners(AuditingEntityListener.class)
public class PackagePA extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String paId;
    private String name;
    private Float compensation;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "packagePA", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<CategoryPA> categoryPAList = new ArrayList<>();

    public PackagePA(String paId, String name, Float compensation, Product product, List<CategoryPA> categoryPAList) {
        this.paId = paId;
        this.name = name;
        this.compensation = compensation;
        this.product = product;
        this.categoryPAList = categoryPAList;
    }

    public PackagePA() {
    }

    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCompensation() {
        return compensation;
    }

    public void setCompensation(Float compensation) {
        this.compensation = compensation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<CategoryPA> getCategoryPAList() {
        return categoryPAList;
    }

    public void setCategoryPAList(List<CategoryPA> categoryPAList) {
        this.categoryPAList = categoryPAList;
    }
}
