package com.cis3760.eauchart.waterwise.repositories.projections;

import java.time.LocalDateTime;

/**
 * Projection to retrieve station information from the inland_lake_drinking_water_quality table
 */
public interface LakeWaterQualityStationProjection {
    Long getId();
    LocalDateTime getLastUpdated();
    String getLakeName();
    String getStationCode();
    String getStationDetails();
}
