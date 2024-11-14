package com.cis3760.eauchart.waterwise.controllers;

import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StreamWaterQualityDTO;
import com.cis3760.eauchart.waterwise.services.StreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api")
public class StreamController {
    private StreamService streamService;

    // Constructor injection for StreamService
    public StreamController(StreamService streamService) {
       this.streamService = streamService;
    }

    /**
     * [GET] /api/stream/v1/stations endpoint: Fetches the stream station information
     * @return ResponseEntity with StationInfoDTOs
     */
    @GetMapping("/stream/v1/stations")
    public ResponseEntity<List<StationInfoDTO>> getStations() {
        return ResponseEntity.status(HttpStatus.OK).body(streamService.getStationInfo());
    }

    /**
     * [GET] /api/stream/v1/stations/{stationCode}/readings: Fetches readings for specified stationCode
     * @param stationCode Station code
     * @return ResponseEntity with list of ReadingInfoDTOs
     */
    @GetMapping("/stream/v1/stations/{stationCode}/readings")
    public Object getReadings(@PathVariable String stationCode) {
        return ResponseEntity.status(HttpStatus.OK).body(streamService.getReadingInfo(stationCode));
    }

    /**
     * [GET] /api/stream/v1/stations/{stationCode}/monthly-averages: Fetches monthly averages for specified stationCode
     * @param stationCode Station code
     * @return ResponseEntity with list of StreamWaterQualityDTOs containing monthly averages for PH, CLIDUR, and PPUT parameters
     */
    @GetMapping("/stream/v1/stations/{stationCode}/monthly-averages")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyAveragesForStation(
        @PathVariable String stationCode) {
        List<Map<String, Object>> data = streamService.getMonthlyAveragesForStation(stationCode);
        return ResponseEntity.ok(data);
    }
}