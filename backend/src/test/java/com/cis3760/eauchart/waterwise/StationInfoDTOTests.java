package com.cis3760.eauchart.waterwise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class StationInfoDTOTests {

    private StationInfoDTO stationInfoDTO;

    @BeforeEach
    public void setUp() {
        stationInfoDTO = new StationInfoDTO();
    }

    @Test
    public void testEmptyConstructor() {
        assertNull(stationInfoDTO.getLastUpdated());
        assertNull(stationInfoDTO.getSector());
        assertNull(stationInfoDTO.getWorksName());
        assertNull(stationInfoDTO.getCompanyCode());
        assertNull(stationInfoDTO.getMunicipality());
        assertNull(stationInfoDTO.getWaterBodyName());
        assertNull(stationInfoDTO.getStationCode());
        assertNull(stationInfoDTO.getStationDetails());
    }

    @Test
    public void testConstructor() {
        LocalDateTime lastUpdated = LocalDateTime.now();

        StationInfoDTO dto = new StationInfoDTO(
                lastUpdated,
                "ORGANIC CHEMICAL MANUFACTURING",  // sector
                "ARLANXEO CANADA INC.",            // worksName
                "0000030007",                      // companyCode
                "SARNIA",                          // municipality
                "Crosson Lake",                    // waterBodyName
                "CN",                              // stationCode
                "03007751001"                      // stationDetails
        );

        assertEquals(lastUpdated, dto.getLastUpdated());
        assertEquals("ORGANIC CHEMICAL MANUFACTURING", dto.getSector());
        assertEquals("ARLANXEO CANADA INC.", dto.getWorksName());
        assertEquals("0000030007", dto.getCompanyCode());
        assertEquals("SARNIA", dto.getMunicipality());
        assertEquals("Crosson Lake", dto.getWaterBodyName());
        assertEquals("CN", dto.getStationCode());
        assertEquals("03007751001", dto.getStationDetails());
    }

    @Test
    public void testSettersAndGetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();

        stationInfoDTO.setLastUpdated(lastUpdated);
        stationInfoDTO.setSector("ORGANIC CHEMICAL MANUFACTURING");
        stationInfoDTO.setWorksName("ARLANXEO CANADA INC.");
        stationInfoDTO.setCompanyCode("0000030007");
        stationInfoDTO.setMunicipality("SARNIA");
        stationInfoDTO.setWaterBodyName("Crosson Lake");
        stationInfoDTO.setStationCode("CN");
        stationInfoDTO.setStationDetails("03007751001");

        assertEquals(lastUpdated, stationInfoDTO.getLastUpdated());
        assertEquals("ORGANIC CHEMICAL MANUFACTURING", stationInfoDTO.getSector());
        assertEquals("ARLANXEO CANADA INC.", stationInfoDTO.getWorksName());
        assertEquals("0000030007", stationInfoDTO.getCompanyCode());
        assertEquals("SARNIA", stationInfoDTO.getMunicipality());
        assertEquals("Crosson Lake", stationInfoDTO.getWaterBodyName());
        assertEquals("CN", stationInfoDTO.getStationCode());
        assertEquals("03007751001", stationInfoDTO.getStationDetails());
    }
}
