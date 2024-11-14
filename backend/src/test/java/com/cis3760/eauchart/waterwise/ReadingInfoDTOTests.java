package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingInfoDTOTests {
    private ReadingInfoDTO readingInfo;

    @BeforeEach
    void setUp() {
        readingInfo = new ReadingInfoDTO();
    }

    @Test
    void testConstructorAndGetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        ReadingInfoDTO readingInfo = new ReadingInfoDTO(
                lastUpdated,
                "2016/01",
                "PLANT - PROCESS EFFLUENT",
                "ALUMINIUM, UNFILTERED TOTAL",
                "MONTHLY",
                "AVERAGE",
                "11.529",
                "KG/D",
                "0.850",
                "1.180",
                "0.240",
                "14.900",
                "1.140",
                "4.300",
                "150.000",
                "0.240",
                "0.360",
                "0.550",
                "6.000",
                "56.000",
                "257.000",
                "313.000",
                "5.60",
                "8.000",
                "1.320",
                "2.850",
                "AGUT",
                "SILVER, UNFILTERED TOTAL",
                "2016-01-01",
                "2016-04-11T00:00:00.000Z",
                "1970-01-01T13:30:00.000Z",
                "C228001",
                "",
                "-4.080",
                "+/-6.0",
                "MICROGRAM PER LITER",
                "E3497"
        );

        assertEquals(lastUpdated, readingInfo.getLastUpdated());
        assertEquals("2016/01", readingInfo.getSampleDate());
        assertEquals("PLANT - PROCESS EFFLUENT", readingInfo.getControlPointName());
        assertEquals("ALUMINIUM, UNFILTERED TOTAL", readingInfo.getParameterName());
        assertEquals("MONTHLY", readingInfo.getFrequency());
        assertEquals("AVERAGE", readingInfo.getComponentType());
        assertEquals("11.529", readingInfo.getMeasuredValue());
        assertEquals("KG/D", readingInfo.getUnitOfMeasure());
        assertEquals("0.850", readingInfo.getGranAlkalinity());
        assertEquals("1.180", readingInfo.getCalcium());
        assertEquals("0.240", readingInfo.getChloride());
        assertEquals("14.900", readingInfo.getSpecificConductance());
        assertEquals("1.140", readingInfo.getDic());
        assertEquals("4.300", readingInfo.getDoc());
        assertEquals("150.000", readingInfo.getIron());
        assertEquals("0.240", readingInfo.getPotassium());
        assertEquals("0.360", readingInfo.getMagnesium());
        assertEquals("0.550", readingInfo.getSodium());
        assertEquals("6.000", readingInfo.getAmmoniumAmmonia());
        assertEquals("56.000", readingInfo.getNitrateNitrite());
        assertEquals("257.000", readingInfo.getTkn());
        assertEquals("313.000", readingInfo.getTn());
        assertEquals("5.60", readingInfo.getPh());
        assertEquals("8.000", readingInfo.getTotalPhosphorus());
        assertEquals("1.320", readingInfo.getSilicate());
        assertEquals("2.850", readingInfo.getSulfate());
        assertEquals("AGUT", readingInfo.getParameterCode());
        assertEquals("SILVER, UNFILTERED TOTAL", readingInfo.getParameterDescription());
        assertEquals("2016-01-01", readingInfo.getYear());
        assertEquals("2016-04-11T00:00:00.000Z", readingInfo.getDate());
        assertEquals("1970-01-01T13:30:00.000Z", readingInfo.getTime());
        assertEquals("C228001", readingInfo.getFieldNumber());
        assertEquals("", readingInfo.getRemarkCode());
        assertEquals("-4.080", readingInfo.getResult());
        assertEquals("+/-6.0", readingInfo.getValueQualifier());
        assertEquals("MICROGRAM PER LITER", readingInfo.getUnits());
        assertEquals("E3497", readingInfo.getAnalysisMethod());
    }

    @Test
    void testSetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        readingInfo.setLastUpdated(lastUpdated);
        readingInfo.setSampleDate("2016/01");
        readingInfo.setControlPointName("PLANT - PROCESS EFFLUENT");
        readingInfo.setParameterName("ALUMINIUM, UNFILTERED TOTAL");
        readingInfo.setFrequency("MONTHLY");
        readingInfo.setComponentType("AVERAGE");
        readingInfo.setMeasuredValue("11.529");
        readingInfo.setUnitOfMeasure("KG/D");
        readingInfo.setGranAlkalinity("0.850");
        readingInfo.setCalcium("1.180");
        readingInfo.setChloride("0.240");
        readingInfo.setSpecificConductance("14.900");
        readingInfo.setDic("1.140");
        readingInfo.setDoc("4.300");
        readingInfo.setIron("150.000");
        readingInfo.setPotassium("0.240");
        readingInfo.setMagnesium("0.360");
        readingInfo.setSodium("0.550");
        readingInfo.setAmmoniumAmmonia("6.000");
        readingInfo.setNitrateNitrite("56.000");
        readingInfo.setTkn("257.000");
        readingInfo.setTn("313.000");
        readingInfo.setPh("5.60");
        readingInfo.setTotalPhosphorus("8.000");
        readingInfo.setSilicate("1.320");
        readingInfo.setSulfate("2.850");
        readingInfo.setParameterCode("AGUT");
        readingInfo.setParameterDescription("SILVER, UNFILTERED TOTAL");
        readingInfo.setYear("2016-01-01");
        readingInfo.setDate("2016-04-11T00:00:00.000Z");
        readingInfo.setTime("1970-01-01T13:30:00.000Z");
        readingInfo.setFieldNumber("C228001");
        readingInfo.setRemarkCode("");
        readingInfo.setResult("-4.080");
        readingInfo.setValueQualifier("+/-6.0");
        readingInfo.setUnits("MICROGRAM PER LITER");
        readingInfo.setAnalysisMethod("E3497");

        assertEquals(lastUpdated, readingInfo.getLastUpdated());
        assertEquals("2016/01", readingInfo.getSampleDate());
        assertEquals("PLANT - PROCESS EFFLUENT", readingInfo.getControlPointName());
        assertEquals("ALUMINIUM, UNFILTERED TOTAL", readingInfo.getParameterName());
        assertEquals("MONTHLY", readingInfo.getFrequency());
        assertEquals("AVERAGE", readingInfo.getComponentType());
        assertEquals("11.529", readingInfo.getMeasuredValue());
        assertEquals("KG/D", readingInfo.getUnitOfMeasure());
        assertEquals("0.850", readingInfo.getGranAlkalinity());
        assertEquals("1.180", readingInfo.getCalcium());
        assertEquals("0.240", readingInfo.getChloride());
        assertEquals("14.900", readingInfo.getSpecificConductance());
        assertEquals("1.140", readingInfo.getDic());
        assertEquals("4.300", readingInfo.getDoc());
        assertEquals("150.000", readingInfo.getIron());
        assertEquals("0.240", readingInfo.getPotassium());
        assertEquals("0.360", readingInfo.getMagnesium());
        assertEquals("0.550", readingInfo.getSodium());
        assertEquals("6.000", readingInfo.getAmmoniumAmmonia());
        assertEquals("56.000", readingInfo.getNitrateNitrite());
        assertEquals("257.000", readingInfo.getTkn());
        assertEquals("313.000", readingInfo.getTn());
        assertEquals("5.60", readingInfo.getPh());
        assertEquals("8.000", readingInfo.getTotalPhosphorus());
        assertEquals("1.320", readingInfo.getSilicate());
        assertEquals("2.850", readingInfo.getSulfate());
        assertEquals("AGUT", readingInfo.getParameterCode());
        assertEquals("SILVER, UNFILTERED TOTAL", readingInfo.getParameterDescription());
        assertEquals("2016-01-01", readingInfo.getYear());
        assertEquals("2016-04-11T00:00:00.000Z", readingInfo.getDate());
        assertEquals("1970-01-01T13:30:00.000Z", readingInfo.getTime());
        assertEquals("C228001", readingInfo.getFieldNumber());
        assertEquals("", readingInfo.getRemarkCode());
        assertEquals("-4.080", readingInfo.getResult());
        assertEquals("+/-6.0", readingInfo.getValueQualifier());
        assertEquals("MICROGRAM PER LITER", readingInfo.getUnits());
        assertEquals("E3497", readingInfo.getAnalysisMethod());
    }
}
