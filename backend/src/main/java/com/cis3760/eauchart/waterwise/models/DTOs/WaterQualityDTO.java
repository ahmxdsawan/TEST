package com.cis3760.eauchart.waterwise.models.DTOs;

public class WaterQualityDTO {
    private String sampleDate;
    private String lakeName;
    private String ph;
    private String chloride;
    private String totalPhosphorus;
    private String sulfate;
    private String toxicityScore;

    // Constructors
    public WaterQualityDTO() {}

    public WaterQualityDTO(String sampleDate, String lakeName, String ph, String chloride, String totalPhosphorus, String sulfate, String toxicityScore) {
        this.sampleDate = sampleDate;
        this.lakeName = lakeName;
        this.ph = ph;
        this.chloride = chloride;
        this.totalPhosphorus = totalPhosphorus;
        this.sulfate = sulfate;
        this.toxicityScore = toxicityScore;
    }

    // Getters and Setters
    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getLakeName() {
        return lakeName;
    }

    public void setLakeName(String lakeName) {
        this.lakeName = lakeName;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getChloride() {
        return chloride;
    }

    public void setChloride(String chloride) {
        this.chloride = chloride;
    }

    public String getTotalPhosphorus() {
        return totalPhosphorus;
    }

    public void setTotalPhosphorus(String totalPhosphorus) {
        this.totalPhosphorus = totalPhosphorus;
    }

    public String getSulfate() {
        return sulfate;
    }

    public void setSulfate(String sulfate) {
        this.sulfate = sulfate;
    }

    public String getToxicityScore() {
        return toxicityScore;
    }

    public void setToxicityScore(String toxicityScore) {
        this.toxicityScore = toxicityScore;
    }
}
