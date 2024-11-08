package com.poseidon.cis3760.spring.template.contaminants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ContaminantsMinMaxData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("facility_name")
    private String facility_name;

    @JsonProperty("sample_date")
    private String sample_date;

    @JsonProperty("contaminant")
    private String contaminant;

    @JsonProperty("reported_as")
    private String reported_as;

    @JsonProperty("average")
    private Double average;
    
    @JsonProperty("maximum")
    private Double maximum;

    @JsonProperty("minimum")
    private Double minimum;

    @JsonProperty("units")
    private String units;

    public ContaminantsMinMaxData() {

    }

    public ContaminantsMinMaxData(Integer id, String sector, String facility_name, String sample_date, String contaminant, 
            String reported_as, Double average, Double maximum, Double minimum, String units) {
        this.id = id;
        this.sector = sector;
        this.facility_name = facility_name;
        this.sample_date = sample_date;
        this.contaminant = contaminant;
        this.reported_as = reported_as;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
        this.units = units;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getSector() {
        return sector;
    }

    public String getFacilityName() {
        return facility_name;
    }

    public String getSampleDate() {
        return sample_date;
    }

    public String getContaminant() {
        return contaminant;
    }

    public String getReportedAs() {
        return reported_as;
    }

    public Double getAverage() {
        return average;
    }

    public Double getMaximum() {
        return maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public String getUnits() {
        return units;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setFacilityName(String facility_name) {
        this.facility_name = facility_name;
    }

    public void setSampleDate(String sample_date) {
        this.sample_date = sample_date;
    }

    public void setContaminant(String contaminant) {
        this.contaminant = contaminant;
    }

    public void setReportedAs(String reported_as) {
        this.reported_as = reported_as;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
