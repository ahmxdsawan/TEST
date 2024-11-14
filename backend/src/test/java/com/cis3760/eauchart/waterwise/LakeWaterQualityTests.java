package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.models.LakeWaterQuality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LakeWaterQualityTests {
    private LakeWaterQuality lakeWaterQuality;

    @BeforeEach
    void setUp() {
        lakeWaterQuality = new LakeWaterQuality();
    }

    @Test
    void testConstructorAndGetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        lakeWaterQuality = new LakeWaterQuality(
                1L,
                lastUpdated,
                "Blue Chalk Lake",
                "BC",
                "03007754001",
                "02-May-16",
                new BigDecimal("5.61"),
                new BigDecimal("2.06"),
                new BigDecimal("0.27"),
                new BigDecimal("22.1"),
                new BigDecimal("1.94"),
                new BigDecimal("1.80"),
                new BigDecimal("50"),
                new BigDecimal("0.31"),
                new BigDecimal("0.56"),
                new BigDecimal("0.68"),
                new BigDecimal("4"),
                new BigDecimal("42"),
                new BigDecimal("163"),
                new BigDecimal("205"),
                new BigDecimal("6.39"),
                new BigDecimal("5.1"),
                new BigDecimal("0.66"),
                new BigDecimal("3.3")
        );

        assertEquals(1L, lakeWaterQuality.getId());
        assertEquals(lastUpdated, lakeWaterQuality.getLast_updated());
        assertEquals("Blue Chalk Lake", lakeWaterQuality.getLakeName());
        assertEquals("BC", lakeWaterQuality.getStationCode());
        assertEquals("03007754001", lakeWaterQuality.getStationDetails());
        assertEquals("02-May-16", lakeWaterQuality.getSampleDate());
        assertEquals(new BigDecimal("5.61"), lakeWaterQuality.getGranAlkalinity());
        assertEquals(new BigDecimal("2.06"), lakeWaterQuality.getCalcium());
        assertEquals(new BigDecimal("0.27"), lakeWaterQuality.getChloride());
        assertEquals(new BigDecimal("22.1"), lakeWaterQuality.getSpecificConductance());
        assertEquals(new BigDecimal("1.94"), lakeWaterQuality.getDic());
        assertEquals(new BigDecimal("1.80"), lakeWaterQuality.getDoc());
        assertEquals(new BigDecimal("50"), lakeWaterQuality.getIron());
        assertEquals(new BigDecimal("0.31"), lakeWaterQuality.getPotassium());
        assertEquals(new BigDecimal("0.56"), lakeWaterQuality.getMagnesium());
        assertEquals(new BigDecimal("0.68"), lakeWaterQuality.getSodium());
        assertEquals(new BigDecimal("4"), lakeWaterQuality.getAmmoniumAmmonia());
        assertEquals(new BigDecimal("42"), lakeWaterQuality.getNitrateNitrite());
        assertEquals(new BigDecimal("163"), lakeWaterQuality.getTkn());
        assertEquals(new BigDecimal("205"), lakeWaterQuality.getTn());
        assertEquals(new BigDecimal("6.39"), lakeWaterQuality.getPh());
        assertEquals(new BigDecimal("5.1"), lakeWaterQuality.getTotalPhosphorus());
        assertEquals(new BigDecimal("0.66"), lakeWaterQuality.getSilicate());
        assertEquals(new BigDecimal("3.3"), lakeWaterQuality.getSulfate());
    }

    @Test
    void testSetters() {
        LocalDateTime newLastUpdated = LocalDateTime.now();
        lakeWaterQuality.setId(2L);
        lakeWaterQuality.setLast_updated(newLastUpdated);
        lakeWaterQuality.setLakeName("Chub Lake");
        lakeWaterQuality.setStationCode("CB");
        lakeWaterQuality.setStationDetails("03008553001");
        lakeWaterQuality.setSampleDate("28-Jun-16");
        lakeWaterQuality.setGranAlkalinity(new BigDecimal("6.00"));
        lakeWaterQuality.setCalcium(new BigDecimal("2.50"));
        lakeWaterQuality.setChloride(new BigDecimal("0.30"));
        lakeWaterQuality.setSpecificConductance(new BigDecimal("25.0"));
        lakeWaterQuality.setDic(new BigDecimal("2.00"));
        lakeWaterQuality.setDoc(new BigDecimal("2.00"));
        lakeWaterQuality.setIron(new BigDecimal("60"));
        lakeWaterQuality.setPotassium(new BigDecimal("0.40"));
        lakeWaterQuality.setMagnesium(new BigDecimal("0.70"));
        lakeWaterQuality.setSodium(new BigDecimal("0.90"));
        lakeWaterQuality.setAmmoniumAmmonia(new BigDecimal("5"));
        lakeWaterQuality.setNitrateNitrite(new BigDecimal("50"));
        lakeWaterQuality.setTkn(new BigDecimal("170"));
        lakeWaterQuality.setTn(new BigDecimal("210"));
        lakeWaterQuality.setPh(new BigDecimal("6.50"));
        lakeWaterQuality.setTotalPhosphorus(new BigDecimal("6.0"));
        lakeWaterQuality.setSilicate(new BigDecimal("0.70"));
        lakeWaterQuality.setSulfate(new BigDecimal("4.0"));

        assertEquals(2L, lakeWaterQuality.getId());
        assertEquals(newLastUpdated, lakeWaterQuality.getLast_updated());
        assertEquals("Chub Lake", lakeWaterQuality.getLakeName());
        assertEquals("CB", lakeWaterQuality.getStationCode());
        assertEquals("03008553001", lakeWaterQuality.getStationDetails());
        assertEquals("28-Jun-16", lakeWaterQuality.getSampleDate());
        assertEquals(new BigDecimal("6.00"), lakeWaterQuality.getGranAlkalinity());
        assertEquals(new BigDecimal("2.50"), lakeWaterQuality.getCalcium());
        assertEquals(new BigDecimal("0.30"), lakeWaterQuality.getChloride());
        assertEquals(new BigDecimal("25.0"), lakeWaterQuality.getSpecificConductance());
        assertEquals(new BigDecimal("2.00"), lakeWaterQuality.getDic());
        assertEquals(new BigDecimal("2.00"), lakeWaterQuality.getDoc());
        assertEquals(new BigDecimal("60"), lakeWaterQuality.getIron());
        assertEquals(new BigDecimal("0.40"), lakeWaterQuality.getPotassium());
        assertEquals(new BigDecimal("0.70"), lakeWaterQuality.getMagnesium());
        assertEquals(new BigDecimal("0.90"), lakeWaterQuality.getSodium());
        assertEquals(new BigDecimal("5"), lakeWaterQuality.getAmmoniumAmmonia());
        assertEquals(new BigDecimal("50"), lakeWaterQuality.getNitrateNitrite());
        assertEquals(new BigDecimal("170"), lakeWaterQuality.getTkn());
        assertEquals(new BigDecimal("210"), lakeWaterQuality.getTn());
        assertEquals(new BigDecimal("6.50"), lakeWaterQuality.getPh());
        assertEquals(new BigDecimal("6.0"), lakeWaterQuality.getTotalPhosphorus());
        assertEquals(new BigDecimal("0.70"), lakeWaterQuality.getSilicate());
        assertEquals(new BigDecimal("4.0"), lakeWaterQuality.getSulfate());
    }
}
