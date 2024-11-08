package com.poseidon.cis3760.spring.template.contaminants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "wastewater_samples")
public class ContaminantsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("sector_route_name")
    private String sector_route_name;

    @JsonProperty("sector_display_name")
    private String sector_display_name;

    @JsonProperty("facility_name")
    private String facility_name;

    @JsonProperty("sample_date")
    private String sample_date;

    @JsonProperty("contaminant_route_name")
    private String contaminant_route_name;

    @JsonProperty("contaminant_display_name")
    private String contaminant_display_name;

    @JsonProperty("reported_as")
    private String reported_as;

    @JsonProperty("component_type")
    private String component_type;

    @JsonProperty("value")
    private Double value;

    @JsonProperty("units")
    private String units;

    public ContaminantsData() {

    }

    public ContaminantsData(Integer id, String sector_route_name, String sector_display_name, 
            String facility_name, String sample_date, String contaminant_route_name, String contaminant_display_name,
            String reported_as, String component_type, Double value, String units) {
        this.id = id;
        this.sector_route_name = sector_route_name;
        this.sector_display_name = sector_display_name;
        this.facility_name = facility_name;
        this.sample_date = sample_date;
        this.contaminant_route_name = contaminant_route_name;
        this.contaminant_display_name = contaminant_display_name;
        this.reported_as = reported_as;
        this.component_type = component_type;
        this.value = value;
        this.units = units;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getSectorRouteName() {
        return sector_route_name;
    }

    public String getSectorDisplayName() {
        return sector_display_name;
    }

    public String getFacilityName() {
        return facility_name;
    }

    public String getSampleDate() {
        return sample_date;
    }

    public String getContaminantRouteName() {
        return contaminant_route_name;
    }

    public String getContaminantDisplayName() {
        return contaminant_display_name;
    }

    public String getReportedAs() {
        return reported_as;
    }

    public String getComponentType() {
        return component_type;
    }

    public Double getValue() {
        return value;
    }

    public String getUnits() {
        return units;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSectorRouteName(String sector_route_name) {
        this.sector_route_name = sector_route_name;
    }

    public void setSectorDisplayName(String sector_display_name) {
        this.sector_display_name = sector_display_name;
    }

    public void setFacilityName(String facility_name) {
        this.facility_name = facility_name;
    }

    public void setSampleDate(String sample_date) {
        this.sample_date = sample_date;
    }

    public void setContaminantRouteName(String contaminant_route_name) {
        this.contaminant_route_name = contaminant_route_name;
    }

    public void setContaminantDisplayName(String contaminant_display_name) {
        this.contaminant_display_name = contaminant_display_name;
    }

    public void setReportedAs(String reported_as) {
        this.reported_as = reported_as;
    }

    public void setComponentType(String component_type) {
        this.component_type = component_type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
