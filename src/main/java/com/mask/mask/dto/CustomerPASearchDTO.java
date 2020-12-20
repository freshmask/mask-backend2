package com.mask.mask.dto;

public class CustomerPASearchDTO {
    private String searchName;
    private String searchIdentityNo;
    private String searchIdentityType;
    private String searchPhoneNumber;
    private String searchEmail;
    private String searchAddress;
    private String searchHeirName;
    private String searchRelationship;
    private String searchHeirPhoneNumber;
    private String searchHeirEmail;

    public CustomerPASearchDTO(String searchName, String searchIdentityNo, String searchIdentityType, String searchPhoneNumber, String searchEmail, String searchAddress, String searchHeirName, String searchRelationship, String searchHeirPhoneNumber, String searchHeirEmail) {
        this.searchName = searchName;
        this.searchIdentityNo = searchIdentityNo;
        this.searchIdentityType = searchIdentityType;
        this.searchPhoneNumber = searchPhoneNumber;
        this.searchEmail = searchEmail;
        this.searchAddress = searchAddress;
        this.searchHeirName = searchHeirName;
        this.searchRelationship = searchRelationship;
        this.searchHeirPhoneNumber = searchHeirPhoneNumber;
        this.searchHeirEmail = searchHeirEmail;
    }

    public CustomerPASearchDTO() {
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchIdentityNo() {
        return searchIdentityNo;
    }

    public void setSearchIdentityNo(String searchIdentityNo) {
        this.searchIdentityNo = searchIdentityNo;
    }

    public String getSearchIdentityType() {
        return searchIdentityType;
    }

    public void setSearchIdentityType(String searchIdentityType) {
        this.searchIdentityType = searchIdentityType;
    }

    public String getSearchPhoneNumber() {
        return searchPhoneNumber;
    }

    public void setSearchPhoneNumber(String searchPhoneNumber) {
        this.searchPhoneNumber = searchPhoneNumber;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public String getSearchAddress() {
        return searchAddress;
    }

    public void setSearchAddress(String searchAddress) {
        this.searchAddress = searchAddress;
    }

    public String getSearchHeirName() {
        return searchHeirName;
    }

    public void setSearchHeirName(String searchHeirName) {
        this.searchHeirName = searchHeirName;
    }

    public String getSearchRelationship() {
        return searchRelationship;
    }

    public void setSearchRelationship(String searchRelationship) {
        this.searchRelationship = searchRelationship;
    }

    public String getSearchHeirPhoneNumber() {
        return searchHeirPhoneNumber;
    }

    public void setSearchHeirPhoneNumber(String searchHeirPhoneNumber) {
        this.searchHeirPhoneNumber = searchHeirPhoneNumber;
    }

    public String getSearchHeirEmail() {
        return searchHeirEmail;
    }

    public void setSearchHeirEmail(String searchHeirEmail) {
        this.searchHeirEmail = searchHeirEmail;
    }
}
