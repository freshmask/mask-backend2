package com.mask.mask.dto;

public class ProductSearchDTO {
    private String searchProductName;
    private String searchIsActive;

    public ProductSearchDTO(String searchProductName, String searchIsActive) {
        this.searchProductName = searchProductName;
        this.searchIsActive = searchIsActive;
    }

    public String getSearchProductName() {
        return searchProductName;
    }

    public void setSearchProductName(String searchProductName) {
        this.searchProductName = searchProductName;
    }

    public String getSearchIsActive() {
        return searchIsActive;
    }

    public void setSearchIsActive(String searchIsActive) {
        this.searchIsActive = searchIsActive;
    }
}
