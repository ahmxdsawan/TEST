package com.cis3760.eauchart.waterwise.repositories.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface LakeWaterQualityReadingProjection {
    LocalDateTime getLastUpdated();
    String getSampleDate();
    String getLakeName();
    BigDecimal getGranAlkalinity();
    BigDecimal getCalcium();
    BigDecimal getChloride();
    BigDecimal getSpecificConductance();
    BigDecimal getDic();
    BigDecimal getDoc();
    BigDecimal getIron();
    BigDecimal getPotassium();
    BigDecimal getMagnesium();
    BigDecimal getSodium();
    BigDecimal getAmmoniumAmmonia();
    BigDecimal getNitrateNitrite();
    BigDecimal getTkn();
    BigDecimal getTn();
    BigDecimal getPh();
    BigDecimal getTotalPhosphorus();
    BigDecimal getSilicate();
    BigDecimal getSulfate();
    BigDecimal getToxicityScore();
}
