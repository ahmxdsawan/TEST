package com.cis3760.eauchart.waterwise.services;

import com.cis3760.eauchart.waterwise.models.DTOs.ChemicalInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;

import java.util.List;

public interface IndustrialWastewaterService {
    /**
     * Fetch the industrial wastewater station information
     * @return Industrial wastewater station information
     */
    List<StationInfoDTO> getStationInfo();

    /**
     * Fetch the industrial wastewater station information
     * @return Industrial wastewater station information
     */
    List<ReadingInfoDTO> getReadingInfo(String companyCode);

    /**
     * Fetch the industrial wastewater station readings for a specific chemical (for graph data)
     * @return Industrial wastewater station chemical information
     */
    List<ChemicalInfoDTO> getChemicalReadings(String companyCode);
}
