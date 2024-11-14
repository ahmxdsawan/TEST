package com.cis3760.eauchart.waterwise.controllers;

import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.WaterQualityDTO;
import com.cis3760.eauchart.waterwise.services.InlandLakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class InlandLakeController {
    private InlandLakeService inlandLakeService;

    // Constructor injection for InlandLakeService
    public InlandLakeController(InlandLakeService inlandLakeService) {
       this.inlandLakeService = inlandLakeService;
    }

    /**
     * [GET] /api/inland-lake/v1/stations endpoint: Fetches the inland lake station information
     * @return ResponseEntity with StationInfoDTOs
     */
    @GetMapping("/inland-lake/v1/stations")
    public ResponseEntity<List<StationInfoDTO>> getStations() {
        return ResponseEntity.status(HttpStatus.OK).body(inlandLakeService.getStationInfo());
    }

    @GetMapping("/inland-lake/v1/stations/{stationCode}/readings")
    public Object getReadings(@PathVariable String stationCode) {
        return ResponseEntity.status(HttpStatus.OK).body(inlandLakeService.getReadingInfo(stationCode));
    }

    @GetMapping("/inland-lake/v1/lake/{lakeName}/quality")
    public ResponseEntity<List<WaterQualityDTO>> getWaterQualityByLake(@PathVariable String lakeName) {
        List<WaterQualityDTO> qualityData = inlandLakeService.fetchWaterQualityByLake(lakeName);
        return ResponseEntity.status(HttpStatus.OK).body(qualityData);
    }
}