package com.cis3760.eauchart.waterwise.repositories.projections;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public interface StreamWaterQualityReadingProjection {
    String getParameterCode();
    String getParameterDescription();
    String getYear();
    Date getDate();
    Time getTime();
    String getFieldNumber();
    String getRemarkCode();
    BigDecimal getResult();
    String getValueQualifier();
    String getUnits();
    String getAnalysisMethod();
}
