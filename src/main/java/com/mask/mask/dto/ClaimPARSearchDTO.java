package com.mask.mask.dto;

public class ClaimPARSearchDTO {
    private String searchName;
    private String searchEmail;
    private String searchLossCause;
    private String searchIncidentLocation;
    private String searchProductType;

    public ClaimPARSearchDTO(String searchName, String searchEmail, String searchLossCause, String searchIncidentLocation, String searchProductType) {
        this.searchName = searchName;
        this.searchEmail = searchEmail;
        this.searchLossCause = searchLossCause;
        this.searchIncidentLocation = searchIncidentLocation;
        this.searchProductType = searchProductType;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public String getSearchLossCause() {
        return searchLossCause;
    }

    public void setSearchLossCause(String searchLossCause) {
        this.searchLossCause = searchLossCause;
    }

    public String getSearchIncidentLocation() {
        return searchIncidentLocation;
    }

    public void setSearchIncidentLocation(String searchIncidentLocation) {
        this.searchIncidentLocation = searchIncidentLocation;
    }

    public String getSearchProductType() {
        return searchProductType;
    }

    public void setSearchProductType(String searchProductType) {
        this.searchProductType = searchProductType;
    }
}
