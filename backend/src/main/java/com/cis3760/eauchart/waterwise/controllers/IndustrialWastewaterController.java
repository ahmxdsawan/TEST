package com.cis3760.eauchart.waterwise.controllers;

import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.ChemicalInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.services.IndustrialWastewaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class IndustrialWastewaterController {

    private IndustrialWastewaterService industrialWastewaterService;

    // Constructor injection for IndustrialWastewaterService
    public IndustrialWastewaterController(IndustrialWastewaterService industrialWastewaterService) {
       this.industrialWastewaterService = industrialWastewaterService;
    }

    /**
     * [GET] /api/industrial-wastewater/v1/stations endpoint: Fetches the industrial wastewater station information
     * @return Response with list of industrial wastewater stations
     */
    @GetMapping("/industrial-wastewater/v1/stations")
    public ResponseEntity<List<StationInfoDTO>> getStations() {
        return ResponseEntity.status(HttpStatus.OK).body(industrialWastewaterService.getStationInfo());
    }

    /**
     * [GET] /api/industrial-wastewater/v1/stations/{companyCode}/readings endpoint: Fetches the industrial wastewater station readings
     * @return Response with list of industrial wastewater station readings
     */
    @GetMapping("/industrial-wastewater/v1/stations/{companyCode}/readings")
    public ResponseEntity<List<ReadingInfoDTO>> getReadings(@PathVariable String companyCode) {
        return ResponseEntity.status(HttpStatus.OK).body(industrialWastewaterService.getReadingInfo(companyCode));
    }

    /**
     * [GET] /api/industrial-wastewater/v1/stations/{companyCode}/readings/chemicals/{chemicalCode} endpoint: Fetches the industrial wastewater station readings
     * @return Response with list of industrial wastewater station readings
     */
    @GetMapping("/industrial-wastewater/v1/stations/{companyCode}/readings/chemicals")
    public ResponseEntity<List<ChemicalInfoDTO>> getChemicalReadings(@PathVariable String companyCode) {
        return ResponseEntity.status(HttpStatus.OK).body(industrialWastewaterService.getChemicalReadings(companyCode));
    }
}