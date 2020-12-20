package com.mask.mask.dto;

public class PackageTravelSearchDTO {
    private String searchName;
    private Integer searchDays;
    private Float searchPrice;
    private Float searchPricePromo;
    private String searchVoucher;

    public PackageTravelSearchDTO(String searchName, Integer searchDays, Float searchPrice, Float searchPricePromo, String searchVoucher) {
        this.searchName = searchName;
        this.searchDays = searchDays;
        this.searchPrice = searchPrice;
        this.searchPricePromo = searchPricePromo;
        this.searchVoucher = searchVoucher;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
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
