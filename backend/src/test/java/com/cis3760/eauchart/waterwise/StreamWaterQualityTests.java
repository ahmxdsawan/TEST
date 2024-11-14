package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.models.StreamWaterQuality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StreamWaterQualityTests {
    private StreamWaterQuality streamWaterQuality;

    @BeforeEach
    void setUp() {
        streamWaterQuality = new StreamWaterQuality();
    }

    @Test
    void testConstructorAndGetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        Date sampleDate = Date.valueOf("2016-01-11");
        Time sampleTime = Time.valueOf("14:00:00");

        streamWaterQuality = new StreamWaterQuality(
                1L,
                lastUpdated,
                "11000100502",
                "AGUT",
                "SILVER,   UNFILTERED TOTAL",
                2016,
                sampleDate,
                sampleTime,
                "C226442",
                "",
                new BigDecimal("-3.51"),
                "+/-6.0",
                "MICROGRAM PER LITER",
                "E3497"
        );

        assertEquals(1L, streamWaterQuality.getId());
        assertEquals(lastUpdated, streamWaterQuality.getLastUpdated());
        assertEquals("11000100502", streamWaterQuality.getStation());
        assertEquals("AGUT", streamWaterQuality.getParmCode());
        assertEquals("SILVER,   UNFILTERED TOTAL", streamWaterQuality.getParmDescription());
        assertEquals(2016, streamWaterQuality.getYear());
        assertEquals(sampleDate, streamWaterQuality.getSampleDate());
        assertEquals(sampleTime, streamWaterQuality.getSampleTime());
        assertEquals("C226442", streamWaterQuality.getFieldNo());
        assertEquals("", streamWaterQuality.getRemarkCode());
        assertEquals(new BigDecimal("-3.51"), streamWaterQuality.getResult());
        assertEquals("+/-6.0", streamWaterQuality.getValueQualifier());
        assertEquals("MICROGRAM PER LITER", streamWaterQuality.getUnits());
        assertEquals("E3497", streamWaterQuality.getAnalysisMethod());
    }

    @Test
    void testSetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        Date sampleDate = Date.valueOf("2016-01-11");
        Time sampleTime = Time.valueOf("14:00:00");

        streamWaterQuality.setId(2L);
        streamWaterQuality.setLastUpdated(lastUpdated);
        streamWaterQuality.setStation("11000100502");
        streamWaterQuality.setParmCode("BAUT");
        streamWaterQuality.setParmDescription("BARIUM,  UNFILTERED TOTAL");
        streamWaterQuality.setYear(2016);
        streamWaterQuality.setSampleDate(sampleDate);
        streamWaterQuality.setSampleTime(sampleTime);
        streamWaterQuality.setFieldNo("C226442");
        streamWaterQuality.setRemarkCode("");
        streamWaterQuality.setResult(new BigDecimal("31.6"));
        streamWaterQuality.setValueQualifier("+/-3.2");
        streamWaterQuality.setUnits("MICROGRAM PER LITER");
        streamWaterQuality.setAnalysisMethod("E3497");

        assertEquals(2L, streamWaterQuality.getId());
        assertEquals(lastUpdated, streamWaterQuality.getLastUpdated());
        assertEquals("11000100502", streamWaterQuality.getStation());
        assertEquals("BAUT", streamWaterQuality.getParmCode());
        assertEquals("BARIUM,  UNFILTERED TOTAL", streamWaterQuality.getParmDescription());
        assertEquals(2016, streamWaterQuality.getYear());
        assertEquals(sampleDate, streamWaterQuality.getSampleDate());
        assertEquals(sampleTime, streamWaterQuality.getSampleTime());
        assertEquals("C226442", streamWaterQuality.getFieldNo());
        assertEquals("", streamWaterQuality.getRemarkCode());
        assertEquals(new BigDecimal("31.6"), streamWaterQuality.getResult());
        assertEquals("+/-3.2", streamWaterQuality.getValueQualifier());
        assertEquals("MICROGRAM PER LITER", streamWaterQuality.getUnits());
        assertEquals("E3497", streamWaterQuality.getAnalysisMethod());
    }
}
