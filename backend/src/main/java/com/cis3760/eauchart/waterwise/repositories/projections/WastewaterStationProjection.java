package com.cis3760.eauchart.waterwise.repositories.projections;

import java.time.LocalDateTime;

/**
 * Projection to retrieve station information from the industrial_wastewater_by_facility table
 */
public interface WastewaterStationProjection {
    Long getId();
    LocalDateTime getLastUpdated();
    String getSector();
    String getWorksName();
    String getCompanyCode();
    String getMunicipality();
}
