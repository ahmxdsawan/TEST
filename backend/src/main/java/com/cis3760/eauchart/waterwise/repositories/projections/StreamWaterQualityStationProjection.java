package com.cis3760.eauchart.waterwise.repositories.projections;

import java.time.LocalDateTime;

/**
 * Projection to retrieve station information from the stream_water_quality table
 */
public interface StreamWaterQualityStationProjection {
    LocalDateTime getLastUpdated();
    String getStationDetails();
    String getStreamName();
}
