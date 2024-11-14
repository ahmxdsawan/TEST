package com.cis3760.eauchart.waterwise.repositories.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface WastewaterChemicalProjection {
    LocalDateTime getLastUpdated();
    String getSampleDate();
    BigDecimal getMeasuredValue();
    String getUnitOfMeasure();
}
