package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.models.Wastewater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WastewaterTests {
    private Wastewater wastewater;

    @BeforeEach
    void setUp() {
        wastewater = new Wastewater();
    }

    @Test
    void testConstructorAndGetters() {
        LocalDateTime lastUpdated = LocalDateTime.now();
        wastewater = new Wastewater(
                1L,
                lastUpdated,
                "ELECTRIC POWER GENERATION",
                "BRUCE POWER INC. - DEVELOPMENT SERVICES A AND B",
                "0001840107",
                "KINCARDINE, MUNICIPALITY",
                "2016/01",
                "PLANT NON-EVENT PROCESS EFFLUENT",
                "AMMONIUM+AMMONIA, TOTAL   FILTER.REAC",
                "MONTHLY",
                "AVERAGE",
                new BigDecimal("1.3431"),
                "KG/D"
        );

        assertEquals(1L, wastewater.getId());
        assertEquals(lastUpdated, wastewater.getLastUpdated());
        assertEquals("ELECTRIC POWER GENERATION", wastewater.getSector());
        assertEquals("BRUCE POWER INC. - DEVELOPMENT SERVICES A AND B", wastewater.getWorksName());
        assertEquals("0001840107", wastewater.getCompanyCode());
        assertEquals("KINCARDINE, MUNICIPALITY", wastewater.getMunicipality());
        assertEquals("2016/01", wastewater.getSampleDate());
        assertEquals("PLANT NON-EVENT PROCESS EFFLUENT", wastewater.getControlPointName());
        assertEquals("AMMONIUM+AMMONIA, TOTAL   FILTER.REAC", wastewater.getParameterName());
        assertEquals("MONTHLY", wastewater.getFrequency());
        assertEquals("AVERAGE", wastewater.getComponentType());
        assertEquals(new BigDecimal("1.3431"), wastewater.getMeasuredValue());
        assertEquals("KG/D", wastewater.getUnitOfMeasure());
    }

    @Test
    void testSetters() {
        LocalDateTime newLastUpdated = LocalDateTime.now();
        wastewater.setId(2L);
        wastewater.setLastUpdated(newLastUpdated);
        wastewater.setSector("IRON AND STEEL");
        wastewater.setWorksName("ARCELORMITTAL DOFASCO G.P.");
        wastewater.setCompanyCode("0001460005");
        wastewater.setMunicipality("HAMILTON, TWP");
        wastewater.setSampleDate("2016/06");
        wastewater.setControlPointName("PLANT - PROCESS EFFLUENT");
        wastewater.setParameterName("ZINC,  UNFILTERED TOTAL");
        wastewater.setFrequency("MONTHLY");
        wastewater.setComponentType("MAXIMUM");
        wastewater.setMeasuredValue(new BigDecimal("7.3036"));
        wastewater.setUnitOfMeasure("KG/D");

        assertEquals(2L, wastewater.getId());
        assertEquals(newLastUpdated, wastewater.getLastUpdated());
        assertEquals("IRON AND STEEL", wastewater.getSector());
        assertEquals("ARCELORMITTAL DOFASCO G.P.", wastewater.getWorksName());
        assertEquals("0001460005", wastewater.getCompanyCode());
        assertEquals("HAMILTON, TWP", wastewater.getMunicipality());
        assertEquals("2016/06", wastewater.getSampleDate());
        assertEquals("PLANT - PROCESS EFFLUENT", wastewater.getControlPointName());
        assertEquals("ZINC,  UNFILTERED TOTAL", wastewater.getParameterName());
        assertEquals("MONTHLY", wastewater.getFrequency());
        assertEquals("MAXIMUM", wastewater.getComponentType());
        assertEquals(new BigDecimal("7.3036"), wastewater.getMeasuredValue());
        assertEquals("KG/D", wastewater.getUnitOfMeasure());
    }
}
