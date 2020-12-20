package com.mask.mask.dto;

public class PackagePASearchDTO {
    private String searchName;
    private Float searchCompensation;

    public PackagePASearchDTO(String searchName, Float searchCompensation) {
        this.searchName = searchName;
        this.searchCompensation = searchCompensation;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Float getSearchCompensation() {
        return searchCompensation;
    }

    public void setSearchCompensation(Float searchCompensation) {
        this.searchCompensation = searchCompensation;
    }
}
