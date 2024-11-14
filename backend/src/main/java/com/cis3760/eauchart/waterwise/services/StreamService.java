package com.cis3760.eauchart.waterwise.services;

import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StreamWaterQualityDTO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface StreamService {
    /**
     * Fetch the stream station information
     * @return Stream station information
     */
    List<StationInfoDTO> getStationInfo();

    /**
     * Fetch the stream station readings
     * @return Stream station readings information
     */
    List<ReadingInfoDTO> getReadingInfo(String stationCode);

    List<Map<String, Object>> getMonthlyAveragesForStation(String stationCode);
}
