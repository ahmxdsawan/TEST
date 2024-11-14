package com.cis3760.eauchart.waterwise.models.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class StreamWaterQualityDTO {
    private Date date;
    private String parameterCode;
    private BigDecimal result;

    // Constructors
    public StreamWaterQualityDTO() {}

    public StreamWaterQualityDTO(Date date, String parameterCode, BigDecimal result) {
        this.date = date;
        this.parameterCode = parameterCode;
        this.result = result;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
