package com.cis3760.eauchart.waterwise.repositories;

import com.cis3760.eauchart.waterwise.models.StreamWaterQualityMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamWaterQualityMonitoringRespository extends JpaRepository<StreamWaterQualityMonitoring, Long> {
}
