package com.cis3760.eauchart.waterwise.repositories.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface WastewaterReadingProjection {
    LocalDateTime getLastUpdated();
    String getSampleDate();
    String getControlPointName();
    String getParameterName();
    String getFrequency();
    String getComponentType();
    BigDecimal getMeasuredValue();
    String getUnitOfMeasure();
}
