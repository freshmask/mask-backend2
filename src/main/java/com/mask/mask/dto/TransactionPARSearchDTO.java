package com.mask.mask.dto;

public class TransactionPARSearchDTO {
    private String searchIsPayment;
    private String searchIsClaim;
    private Float searchPremi;
    private String searchStatusPolis;

    public TransactionPARSearchDTO(String searchIsPayment, String searchIsClaim, Float searchPremi, String searchStatusPolis) {
        this.searchIsPayment = searchIsPayment;
        this.searchIsClaim = searchIsClaim;
        this.searchPremi = searchPremi;
        this.searchStatusPolis = searchStatusPolis;
    }

    public String getSearchIsPayment() {
        return searchIsPayment;
    }

    public void setSearchIsPayment(String searchIsPayment) {
        this.searchIsPayment = searchIsPayment;
    }

    public String getSearchIsClaim() {
        return searchIsClaim;
    }

    public void setSearchIsClaim(String searchIsClaim) {
        this.searchIsClaim = searchIsClaim;
    }

    public Float getSearchPremi() {
        return searchPremi;
    }

    public void setSearchPremi(Float searchPremi) {
        this.searchPremi = searchPremi;
    }

    public String getSearchStatusPolis() {
        return searchStatusPolis;
    }

    public void setSearchStatusPolis(String searchStatusPolis) {
        this.searchStatusPolis = searchStatusPolis;
    }
}
