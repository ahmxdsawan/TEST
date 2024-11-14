package com.cis3760.eauchart.waterwise.models.DTOs;

import java.time.LocalDateTime;

public class ReadingInfoDTO {
    // Attributes
    private LocalDateTime lastUpdated;
    private String sampleDate;
    private String controlPointName;
    private String parameterName;
    private String frequency;
    private String componentType;
    private String measuredValue;
    private String unitOfMeasure;
    private String granAlkalinity;
    private String calcium;
    private String chloride;
    private String specificConductance;
    private String dic;
    private String doc;
    private String iron;
    private String potassium;
    private String magnesium;
    private String sodium;
    private String ammoniumAmmonia;
    private String nitrateNitrite;
    private String tkn;
    private String tn;
    private String ph;
    private String totalPhosphorus;
    private String silicate;
    private String sulfate;
    private String parameterCode;
    private String parameterDescription;
    private String year;
    private String date;
    private String time;
    private String fieldNumber;
    private String remarkCode;
    private String result;
    private String valueQualifier;
    private String units;
    private String analysisMethod;

    // Constructors
    public ReadingInfoDTO() {}

    public ReadingInfoDTO(LocalDateTime lastUpdated, String sampleDate, String controlPointName, String parameterName, String frequency, String componentType, String measuredValue, String unitOfMeasure, String granAlkalinity, String calcium, String chloride, String specificConductance, String dic, String doc, String iron, String potassium, String magnesium, String sodium, String ammoniumAmmonia, String nitrateNitrite, String tkn, String tn, String ph, String totalPhosphorus, String silicate, String sulfate, String parameterCode, String parameterDescription, String year, String date, String time, String fieldNumber, String remarkCode, String result, String valueQualifier, String units, String analysisMethod) {
        this.lastUpdated = lastUpdated;
        this.sampleDate = sampleDate;
        this.controlPointName = controlPointName;
        this.parameterName = parameterName;
        this.frequency = frequency;
        this.componentType = componentType;
        this.measuredValue = measuredValue;
        this.unitOfMeasure = unitOfMeasure;
        this.granAlkalinity = granAlkalinity;
        this.calcium = calcium;
        this.chloride = chloride;
        this.specificConductance = specificConductance;
        this.dic = dic;
        this.doc = doc;
        this.iron = iron;
        this.potassium = potassium;
        this.magnesium = magnesium;
        this.sodium = sodium;
        this.ammoniumAmmonia = ammoniumAmmonia;
        this.nitrateNitrite = nitrateNitrite;
        this.tkn = tkn;
        this.tn = tn;
        this.ph = ph;
        this.totalPhosphorus = totalPhosphorus;
        this.silicate = silicate;
        this.sulfate = sulfate;
        this.parameterCode = parameterCode;
        this.parameterDescription = parameterDescription;
        this.year = year;
        this.date = date;
        this.time = time;
        this.fieldNumber = fieldNumber;
        this.remarkCode = remarkCode;
        this.result = result;
        this.valueQualifier = valueQualifier;
        this.units = units;
        this.analysisMethod = analysisMethod;
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

    public String getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(String measuredValue) {
        this.measuredValue = measuredValue;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getGranAlkalinity() {
        return granAlkalinity;
    }

    public void setGranAlkalinity(String granAlkalinity) {
        this.granAlkalinity = granAlkalinity;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getChloride() {
        return chloride;
    }

    public void setChloride(String chloride) {
        this.chloride = chloride;
    }

    public String getSpecificConductance() {
        return specificConductance;
    }

    public void setSpecificConductance(String specificConductance) {
        this.specificConductance = specificConductance;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getIron() {
        return iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getPotassium() {
        return potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(String magnesium) {
        this.magnesium = magnesium;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getAmmoniumAmmonia() {
        return ammoniumAmmonia;
    }

    public void setAmmoniumAmmonia(String ammoniumAmmonia) {
        this.ammoniumAmmonia = ammoniumAmmonia;
    }

    public String getNitrateNitrite() {
        return nitrateNitrite;
    }

    public void setNitrateNitrite(String nitrateNitrite) {
        this.nitrateNitrite = nitrateNitrite;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getTotalPhosphorus() {
        return totalPhosphorus;
    }

    public void setTotalPhosphorus(String totalPhosphorus) {
        this.totalPhosphorus = totalPhosphorus;
    }

    public String getSilicate() {
        return silicate;
    }

    public void setSilicate(String silicate) {
        this.silicate = silicate;
    }

    public String getSulfate() {
        return sulfate;
    }

    public void setSulfate(String sulfate) {
        this.sulfate = sulfate;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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