package com.cis3760.eauchart.waterwise.services;

import com.cis3760.eauchart.waterwise.mappers.StationMapper;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.mappers.ChemicalsMapper;
import com.cis3760.eauchart.waterwise.mappers.ReadingsMapper;
import com.cis3760.eauchart.waterwise.models.DTOs.ChemicalInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.WastewaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustrialWastewaterServiceImpl implements IndustrialWastewaterService {
    @Autowired
    private WastewaterRepository wastewaterRepository;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private ReadingsMapper readingsMapper;
    @Autowired
    private ChemicalsMapper chemicalsMapper;


    @Override
    public List<StationInfoDTO> getStationInfo() {
        return stationMapper.wastewaterStationProjectionToStationInfoDTO(wastewaterRepository.getStations());
    }
    @Override
    public List<ReadingInfoDTO> getReadingInfo(String companyCode) {
        return readingsMapper.wastewaterReadingProjectionToReadingInfoDTO(wastewaterRepository.getReadings(companyCode));
    }
    @Override
    public List<ChemicalInfoDTO> getChemicalReadings(String companyCode) {
        return chemicalsMapper.wastewaterReadingProjectionToChemicalInfoDTO(wastewaterRepository.getChemicalReadings(companyCode));
    }
}
