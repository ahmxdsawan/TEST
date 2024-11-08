package com.poseidon.cis3760.spring.template.sewage.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SewageDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Given
        Integer id = 1;
        String sectorRouteName = "municipal";
        String sectorDisplayName = "Municipal";
        String contaminantRouteName = "ammonia";
        String contaminantDisplayName = "Ammonia";
        String facility = "Central Wastewater Facility";
        Integer contaminantLimit = 10;
        String contaminantUnit = "mg/L";
        String exceedanceStartDate = "2024-01-01";
        String exceedanceEndDate = "2024-01-10";
        Integer numExceedances = 3;

        // When
        SewageData data = new SewageData(id, sectorRouteName, sectorDisplayName,
                contaminantRouteName, contaminantDisplayName,
                facility, contaminantLimit, contaminantUnit, exceedanceStartDate, exceedanceEndDate, numExceedances);

        // Then - Validate each getter returns the expected value
        assertEquals(id, data.getId());
        assertEquals(sectorRouteName, data.getSectorRouteName());
        assertEquals(sectorDisplayName, data.getSectorDisplayName());
        assertEquals(contaminantRouteName, data.getContaminantRouteName());
        assertEquals(contaminantDisplayName, data.getContaminantDisplayName());
        assertEquals(facility, data.getFacility());
        assertEquals(contaminantLimit, data.getContaminantLimit());
        assertEquals(contaminantUnit, data.getContaminantUnit());
        assertEquals(exceedanceStartDate, data.getExceedanceStartDate());
        assertEquals(exceedanceEndDate, data.getExceedanceEndDate());
        assertEquals(numExceedances, data.getNumExceedances());
    }

    @Test
    public void testSetters() {
        // Given
        SewageData data = new SewageData();

        // When - Use setters to set properties
        data.setId(2);
        data.setSectorRouteName("industrial");
        data.setSectorDisplayName("Industrial");
        data.setContaminantRouteName("phosphate");
        data.setContaminantDisplayName("Phosphate");
        data.setFacility("Industrial Wastewater Plant");
        data.setContaminantLimit(15);
        data.setContaminantUnit("ppm");
        data.setExceedanceStartDate("2024-02-01");
        data.setExceedanceEndDate("2024-02-15");
        data.setNumExceedances(5);

        // Then - Validate each getter returns the expected value after setting
        assertEquals(2, data.getId());
        assertEquals("industrial", data.getSectorRouteName());
        assertEquals("Industrial", data.getSectorDisplayName());
        assertEquals("phosphate", data.getContaminantRouteName());
        assertEquals("Phosphate", data.getContaminantDisplayName());
        assertEquals("Industrial Wastewater Plant", data.getFacility());
        assertEquals(15, data.getContaminantLimit());
        assertEquals("ppm", data.getContaminantUnit());
        assertEquals("2024-02-01", data.getExceedanceStartDate());
        assertEquals("2024-02-15", data.getExceedanceEndDate());
        assertEquals(5, data.getNumExceedances());
    }

    @Test
    public void testDefaultConstructor() {
        // When
        SewageData data = new SewageData();

        // Then - Validate that the object is not null
        assertNotNull(data);
    }

}
