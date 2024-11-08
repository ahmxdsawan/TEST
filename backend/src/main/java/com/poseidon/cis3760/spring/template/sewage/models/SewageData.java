package com.poseidon.cis3760.spring.template.sewage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "sewage_data")
public class SewageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("sector_route_name")
    private String sector_route_name;

    @JsonProperty("sector_display_name")
    private String sector_display_name;

    @JsonProperty("contaminant_route_name")
    private String contaminant_route_name;

    @JsonProperty("contaminant_display_name")
    private String contaminant_display_name;

    @JsonProperty("facility")
    private String facility;

    @JsonProperty("contaminant_limit")
    private Integer contaminant_limit;

    @JsonProperty("contaminant_unit")
    private String contaminant_unit;

    @JsonProperty("exceedance_start_date")
    private String exceedance_start_date;

    @JsonProperty("exceedance_end_date")
    private String exceedance_end_date;

    @JsonProperty("num_exceedances")
    private Integer num_exceedances;

    // default constructor

    public SewageData() {

    }

    public SewageData(Integer id, String sector_route_name, String sector_display_name,
            String contaminant_route_name, String contaminant_display_name, String facility,
            Integer contaminant_limit, String contaminant_unit, String exceedance_start_date,
            String exceedance_end_date, Integer num_exceedances) {
        this.id = id;
        this.sector_route_name = sector_route_name;
        this.sector_display_name = sector_display_name;
        this.contaminant_route_name = contaminant_route_name;
        this.contaminant_display_name = contaminant_display_name;
        this.facility = facility;
        this.contaminant_limit = contaminant_limit;
        this.contaminant_unit = contaminant_unit;
        this.exceedance_start_date = exceedance_start_date;
        this.exceedance_end_date = exceedance_end_date;
        this.num_exceedances = num_exceedances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectorRouteName() {
        return sector_route_name;
    }

    public void setSectorRouteName(String sector_route_name) {
        this.sector_route_name = sector_route_name;
    }

    public String getSectorDisplayName() {
        return sector_display_name;
    }

    public void setSectorDisplayName(String sector_display_name) {
        this.sector_display_name = sector_display_name;
    }

    public String getContaminantRouteName() {
        return contaminant_route_name;
    }

    public void setContaminantRouteName(String contaminant_route_name) {
        this.contaminant_route_name = contaminant_route_name;
    }

    public String getContaminantDisplayName() {
        return contaminant_display_name;
    }

    public void setContaminantDisplayName(String contaminant_display_name) {
        this.contaminant_display_name = contaminant_display_name;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Integer getContaminantLimit() {
        return contaminant_limit;
    }

    public void setContaminantLimit(Integer contaminant_limit) {
        this.contaminant_limit = contaminant_limit;
    }

    public String getContaminantUnit() {
        return contaminant_unit;
    }

    public void setContaminantUnit(String contaminant_unit) {
        this.contaminant_unit = contaminant_unit;
    }

    public String getExceedanceStartDate() {
        return exceedance_start_date;
    }

    public void setExceedanceStartDate(String exceedance_start_date) {
        this.exceedance_start_date = exceedance_start_date;
    }

    public String getExceedanceEndDate() {
        return exceedance_end_date;
    }

    public void setExceedanceEndDate(String exceedance_end_date) {
        this.exceedance_end_date = exceedance_end_date;
    }

    public Integer getNumExceedances() {
        return num_exceedances;
    }

    public void setNumExceedances(Integer num_exceedances) {
        this.num_exceedances = num_exceedances;
    }

}
