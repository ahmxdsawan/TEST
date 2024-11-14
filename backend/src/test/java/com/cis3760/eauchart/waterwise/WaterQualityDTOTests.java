package com.cis3760.eauchart.waterwise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.cis3760.eauchart.waterwise.models.DTOs.WaterQualityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaterQualityDTOTests {

    private WaterQualityDTO waterQualityDTO;

    @BeforeEach
    public void setUp() {
        waterQualityDTO = new WaterQualityDTO();
    }

    @Test
    public void testEmptyConstructor() {
        assertNull(waterQualityDTO.getSampleDate());
        assertNull(waterQualityDTO.getLakeName());
        assertNull(waterQualityDTO.getPh());
        assertNull(waterQualityDTO.getChloride());
        assertNull(waterQualityDTO.getTotalPhosphorus());
        assertNull(waterQualityDTO.getSulfate());
        assertNull(waterQualityDTO.getToxicityScore());
    }

    @Test
    public void testConstructor() {
        WaterQualityDTO dto = new WaterQualityDTO(
                "31-Oct-17",
                "Dickie Lake",
                "6.54",
                "3.770",
                "10.500",
                "1.950",
                "22.76"
        );

        assertEquals("31-Oct-17", dto.getSampleDate());
        assertEquals("Dickie Lake", dto.getLakeName());
        assertEquals("6.54", dto.getPh());
        assertEquals("3.770", dto.getChloride());
        assertEquals("10.500", dto.getTotalPhosphorus());
        assertEquals("1.950", dto.getSulfate());
        assertEquals("22.76", dto.getToxicityScore());
    }

    @Test
    public void testSettersAndGetters() {
        waterQualityDTO.setSampleDate("31-Oct-17");
        waterQualityDTO.setLakeName("Dickie Lake");
        waterQualityDTO.setPh("6.54");
        waterQualityDTO.setChloride("3.770");
        waterQualityDTO.setTotalPhosphorus("10.500");
        waterQualityDTO.setSulfate("1.950");
        waterQualityDTO.setToxicityScore("22.76");

        assertEquals("31-Oct-17", waterQualityDTO.getSampleDate());
        assertEquals("Dickie Lake", waterQualityDTO.getLakeName());
        assertEquals("6.54", waterQualityDTO.getPh());
        assertEquals("3.770", waterQualityDTO.getChloride());
        assertEquals("10.500", waterQualityDTO.getTotalPhosphorus());
        assertEquals("1.950", waterQualityDTO.getSulfate());
        assertEquals("22.76", waterQualityDTO.getToxicityScore());
    }
}
