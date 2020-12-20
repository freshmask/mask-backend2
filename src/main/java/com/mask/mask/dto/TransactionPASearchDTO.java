package com.mask.mask.dto;

import java.util.Date;

public class TransactionPASearchDTO {
    private String searchIsPayment;
    private String searchIsClaim;
    private Float searchPremi;
    private String searchStatusPolis;
    private Date startDate;
    private Date endDate;

    public TransactionPASearchDTO(String searchIsPayment, String searchIsClaim, Float searchPremi, String searchStatusPolis, Date startDate) {
        this.searchIsPayment = searchIsPayment;
        this.searchIsClaim = searchIsClaim;
        this.searchPremi = searchPremi;
        this.searchStatusPolis = searchStatusPolis;
        this.startDate = startDate;
    }

    public TransactionPASearchDTO(String searchIsPayment, String searchIsClaim, Float searchPremi, String searchStatusPolis, Date startDate, Date endDate) {
        this.searchIsPayment = searchIsPayment;
        this.searchIsClaim = searchIsClaim;
        this.searchPremi = searchPremi;
        this.searchStatusPolis = searchStatusPolis;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
