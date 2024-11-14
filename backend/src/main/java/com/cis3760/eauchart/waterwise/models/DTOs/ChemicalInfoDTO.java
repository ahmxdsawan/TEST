package com.cis3760.eauchart.waterwise.models.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChemicalInfoDTO {
    // Attributes
    private LocalDateTime lastUpdated;
    private String sampleDate;
    private BigDecimal measuredValue;
    private String unitOfMeasure;

    // Constructors
    public ChemicalInfoDTO() {}

    public ChemicalInfoDTO(LocalDateTime lastUpdated, String sampleDate, BigDecimal measuredValue, String unitOfMeasure) {
        this.lastUpdated = lastUpdated;
        this.sampleDate = sampleDate;
        this.measuredValue = measuredValue;
        this.unitOfMeasure = unitOfMeasure;
    }

    // Getters and Setters for all fields
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public BigDecimal getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(BigDecimal measuredValue) {
        this.measuredValue = measuredValue;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}