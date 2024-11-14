package com.cis3760.eauchart.waterwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name="stream_water_quality")
public class StreamWaterQuality {
    // Attributes of stream_water_quality table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="last_updated")
    private LocalDateTime lastUpdated;
    @Column(name="station")
    private String station;
    @Column(name="parm_code")
    private String parmCode;
    @Column(name="parm_description")
    private String parmDescription;
    @Column(name="year")
    private int year;
    @Column(name="sample_date")
    private Date sampleDate;
    @Column(name="sample_time")
    private Time sampleTime;
    @Column(name="field_no")
    private String fieldNo;
    @Column(name="remark_code")
    private String remarkCode;
    @Column(name="result")
    private BigDecimal result;
    @Column(name="value_qualifier")
    private String valueQualifier;
    @Column(name="units")
    private String units;
    @Column(name="analysis_method")
    private String analysisMethod;

    // Constructors
    public StreamWaterQuality() {
    }

    public StreamWaterQuality(Long id, LocalDateTime lastUpdated, String station, String parmCode, String parmDescription, int year, Date sampleDate, Time sampleTime, String fieldNo, String remarkCode, BigDecimal result, String valueQualifier, String units, String analysisMethod) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.station = station;
        this.parmCode = parmCode;
        this.parmDescription = parmDescription;
        this.year = year;
        this.sampleDate = sampleDate;
        this.sampleTime = sampleTime;
        this.fieldNo = fieldNo;
        this.remarkCode = remarkCode;
        this.result = result;
        this.valueQualifier = valueQualifier;
        this.units = units;
        this.analysisMethod = analysisMethod;
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getParmCode() {
        return parmCode;
    }

    public void setParmCode(String parmCode) {
        this.parmCode = parmCode;
    }

    public String getParmDescription() {
        return parmDescription;
    }

    public void setParmDescription(String parmDescription) {
        this.parmDescription = parmDescription;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }

    public Time getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Time sampleTime) {
        this.sampleTime = sampleTime;
    }

    public String getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(String fieldNo) {
        this.fieldNo = fieldNo;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getValueQualifier() {
        return valueQualifier;
    }

    public void setValueQualifier(String valueQualifier) {
        this.valueQualifier = valueQualifier;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAnalysisMethod() {
        return analysisMethod;
    }

    public void setAnalysisMethod(String analysisMethod) {
        this.analysisMethod = analysisMethod;
    }
}
