package com.mask.mask.dto;

public class CategoryPASearchDTO {
    private Integer searchDays;
    private Float searchPrice;
    private Float searchPricePromo;
    private String searchVoucher;

    public CategoryPASearchDTO(Integer searchDays, Float searchPrice, Float searchPricePromo, String searchVoucher) {
        this.searchDays = searchDays;
        this.searchPrice = searchPrice;
        this.searchPricePromo = searchPricePromo;
        this.searchVoucher = searchVoucher;
    }

    public Integer getSearchDays() {
        return searchDays;
    }

    public void setSearchDays(Integer searchDays) {
        this.searchDays = searchDays;
    }

    public Float getSearchPrice() {
        return searchPrice;
    }

    public void setSearchPrice(Float searchPrice) {
        this.searchPrice = searchPrice;
    }

    public Float getSearchPricePromo() {
        return searchPricePromo;
    }

    public void setSearchPricePromo(Float searchPricePromo) {
        this.searchPricePromo = searchPricePromo;
    }

    public String getSearchVoucher() {
        return searchVoucher;
    }

    public void setSearchVoucher(String searchVoucher) {
        this.searchVoucher = searchVoucher;
    }
}
