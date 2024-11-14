package com.cis3760.eauchart.waterwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="industrial_wastewater_by_facility")
public class Wastewater {
    // Attributes of industrial_wastewater_by_facility table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="last_updated")
    public LocalDateTime lastUpdated;
    @Column(name="sector")
    public String sector;
    @Column(name="works_name")
    public String worksName;
    @Column(name="company_code")
    public String companyCode;
    @Column(name="municipality")
    public String municipality;
    @Column(name="sample_date")
    public String sampleDate;
    @Column(name="control_point_name")
    public String controlPointName;
    @Column(name="parameter_name")
    public String parameterName;
    @Column(name="frequency")
    public String frequency;
    @Column(name="component_type")
    public String componentType;
    @Column(name="measured_value")
    public BigDecimal measuredValue;
    @Column(name="unit_of_measure")
    public String unitOfMeasure;

    // Constructors
    public Wastewater() {
    }

    public Wastewater(Long id, LocalDateTime lastUpdated, String sector, String worksName, String companyCode, String municipality, String sampleDate, String controlPointName, String parameterName, String frequency, String componentType, BigDecimal measuredValue, String unitOfMeasure) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.sector = sector;
        this.worksName = worksName;
        this.companyCode = companyCode;
        this.municipality = municipality;
        this.sampleDate = sampleDate;
        this.controlPointName = controlPointName;
        this.parameterName = parameterName;
        this.frequency = frequency;
        this.componentType = componentType;
        this.measuredValue = measuredValue;
        this.unitOfMeasure = unitOfMeasure;
    }

    // Getters + Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getControlPointName() {
        return controlPointName;
    }

    public void setControlPointName(String controlPointName) {
        this.controlPointName = controlPointName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
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
