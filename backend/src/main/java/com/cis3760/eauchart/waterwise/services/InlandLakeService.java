package com.cis3760.eauchart.waterwise.services;

import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.WaterQualityDTO;

import java.util.List;

public interface InlandLakeService {
    /**
     * Fetch the inland lake station information
     * @return Inland lake station information
     */
    List<StationInfoDTO> getStationInfo();

    /**
     * Fetch the readings from inland lake station
     * @return Inland lake station reading information
     */
    List<ReadingInfoDTO> getReadingInfo(String stationCode);

    /**
     * Fetch the water quality by lake
     * @return Water quality by lake
     */
    List<WaterQualityDTO> fetchWaterQualityByLake(String lakeName);
}
