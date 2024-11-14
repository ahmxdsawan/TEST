package com.cis3760.eauchart.waterwise.models.DTOs;

import java.time.LocalDateTime;

public class StationInfoDTO {
    // Attributes
    public LocalDateTime lastUpdated;
    public String sector;
    public String worksName;
    public String companyCode;
    public String municipality;
    public String waterBodyName;
    public String stationCode;
    public String stationDetails;

    // Constructors
    public StationInfoDTO() {
    }

    public StationInfoDTO(LocalDateTime lastUpdated, String sector, String worksName, String companyCode, String municipality, String waterBodyName, String stationCode, String stationDetails) {
        this.lastUpdated = lastUpdated;
        this.sector = sector;
        this.worksName = worksName;
        this.companyCode = companyCode;
        this.municipality = municipality;
        this.waterBodyName = waterBodyName;
        this.stationCode = stationCode;
        this.stationDetails = stationDetails;
    }

    // Getters + Setters
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getWaterBodyName() {
        return waterBodyName;
    }

    public void setWaterBodyName(String waterBodyName) {
        this.waterBodyName = waterBodyName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationDetails() {
        return stationDetails;
    }

    public void setStationDetails(String stationDetails) {
        this.stationDetails = stationDetails;
    }
}
