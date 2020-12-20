package com.mask.mask.dto;

public class CustomerTravelSearchDTO {
    private String searchName;
    private String searchAddress;
    private String searchIdentityType;
    private String searchIdentityNo;
    private String searchPhoneNumber;
    private String searchEmail;
    private String searchHeirName;
    private String searchHeirPhoneNumber;
    private String searchRelationship;
    private String searchHeirEmail;
    private String searchTravelKind;
    private String searchDepartureCity;
    private String searchDestinationCity;
    private String searchJourneyLength;
    private String searchDeparturePurpose;

    public CustomerTravelSearchDTO(String searchName, String searchAddress, String searchIdentityType, String searchIdentityNo, String searchPhoneNumber, String searchEmail, String searchHeirName, String searchHeirPhoneNumber, String searchRelationship, String searchHeirEmail, String searchTravelKind, String searchDepartureCity, String searchDestinationCity, String searchJourneyLength, String searchDeparturePurpose) {
        this.searchName = searchName;
        this.searchAddress = searchAddress;
        this.searchIdentityType = searchIdentityType;
        this.searchIdentityNo = searchIdentityNo;
        this.searchPhoneNumber = searchPhoneNumber;
        this.searchEmail = searchEmail;
        this.searchHeirName = searchHeirName;
        this.searchHeirPhoneNumber = searchHeirPhoneNumber;
        this.searchRelationship = searchRelationship;
        this.searchHeirEmail = searchHeirEmail;
        this.searchTravelKind = searchTravelKind;
        this.searchDepartureCity = searchDepartureCity;
        this.searchDestinationCity = searchDestinationCity;
        this.searchJourneyLength = searchJourneyLength;
        this.searchDeparturePurpose = searchDeparturePurpose;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchAddress() {
        return searchAddress;
    }

    public void setSearchAddress(String searchAddress) {
        this.searchAddress = searchAddress;
    }

    public String getSearchIdentityType() {
        return searchIdentityType;
    }

    public void setSearchIdentityType(String searchIdentityType) {
        this.searchIdentityType = searchIdentityType;
    }

    public String getSearchIdentityNo() {
        return searchIdentityNo;
    }

    public void setSearchIdentityNo(String searchIdentityNo) {
        this.searchIdentityNo = searchIdentityNo;
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

    public String getSearchHeirName() {
        return searchHeirName;
    }

    public void setSearchHeirName(String searchHeirName) {
        this.searchHeirName = searchHeirName;
    }

    public String getSearchHeirPhoneNumber() {
        return searchHeirPhoneNumber;
    }

    public void setSearchHeirPhoneNumber(String searchHeirPhoneNumber) {
        this.searchHeirPhoneNumber = searchHeirPhoneNumber;
    }

    public String getSearchRelationship() {
        return searchRelationship;
    }

    public void setSearchRelationship(String searchRelationship) {
        this.searchRelationship = searchRelationship;
    }

    public String getSearchHeirEmail() {
        return searchHeirEmail;
    }

    public void setSearchHeirEmail(String searchHeirEmail) {
        this.searchHeirEmail = searchHeirEmail;
    }

    public String getSearchTravelKind() {
        return searchTravelKind;
    }

    public void setSearchTravelKind(String searchTravelKind) {
        this.searchTravelKind = searchTravelKind;
    }

    public String getSearchDepartureCity() {
        return searchDepartureCity;
    }

    public void setSearchDepartureCity(String searchDepartureCity) {
        this.searchDepartureCity = searchDepartureCity;
    }

    public String getSearchDestinationCity() {
        return searchDestinationCity;
    }

    public void setSearchDestinationCity(String searchDestinationCity) {
        this.searchDestinationCity = searchDestinationCity;
    }

    public String getSearchJourneyLength() {
        return searchJourneyLength;
    }

    public void setSearchJourneyLength(String searchJourneyLength) {
        this.searchJourneyLength = searchJourneyLength;
    }

    public String getSearchDeparturePurpose() {
        return searchDeparturePurpose;
    }

    public void setSearchDeparturePurpose(String searchDeparturePurpose) {
        this.searchDeparturePurpose = searchDeparturePurpose;
    }
}
