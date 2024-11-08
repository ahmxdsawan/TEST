package com.poseidon.cis3760.spring.template.contaminants.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaminantsDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Given
        Integer id = 1;
        String sector_route_name = "wastewater-treatment";
        String sector_display_name = "Wastewater Treatment";
        String facilityName = "City Sewage Plant A";
        String sampleDate = "2024-05-15";
        String contaminant_route_name = "nitrate";
        String contaminant_display_name = "Nitrate";
        String reportedAs = "Above Safe Limit";
        String componentType = "Nutrient";
        Double value = 45.6;
        String units = "mg/L";

        // When
        ContaminantsData data = new ContaminantsData(id, sector_route_name, sector_display_name, facilityName,
                sampleDate, contaminant_route_name, contaminant_display_name, reportedAs, componentType,
                value, units);

        // Then - Validate each getter returns the expected value
        assertEquals(id, data.getId());
        assertEquals(sector_route_name, data.getSectorRouteName());
        assertEquals(sector_display_name, data.getSectorDisplayName());
        assertEquals(facilityName, data.getFacilityName());
        assertEquals(sampleDate, data.getSampleDate());
        assertEquals(contaminant_route_name, data.getContaminantRouteName());
        assertEquals(contaminant_display_name, data.getContaminantDisplayName());
        assertEquals(reportedAs, data.getReportedAs());
        assertEquals(componentType, data.getComponentType());
        assertEquals(value, data.getValue());
        assertEquals(units, data.getUnits());
    }

    @Test
    public void testSetters() {
        // Given
        ContaminantsData data = new ContaminantsData();

        // When - Use setters to set properties
        data.setId(1);
        data.setSectorRouteName("commercial-one");
        data.setSectorDisplayName("Commercial One");
        data.setFacilityName("Sample Facility");
        data.setSampleDate("2024-01-01");
        data.setContaminantRouteName("mercury");
        data.setContaminantDisplayName("Mercury");
        data.setReportedAs("Medium");
        data.setComponentType("Toxic Element");
        data.setValue(15.5);
        data.setUnits("mg/L");

        // Then - Validate each getter returns the expected value after setting
        assertEquals(1, data.getId());
        assertEquals("commercial-one", data.getSectorRouteName());
        assertEquals("Commercial One", data.getSectorDisplayName());
        assertEquals("Sample Facility", data.getFacilityName());
        assertEquals("2024-01-01", data.getSampleDate());
        assertEquals("mercury", data.getContaminantRouteName());
        assertEquals("Mercury", data.getContaminantDisplayName());
        assertEquals("Medium", data.getReportedAs());
        assertEquals("Toxic Element", data.getComponentType());
        assertEquals(15.5, data.getValue());
        assertEquals("mg/L", data.getUnits());
    }

    @Test
    public void testDefaultConstructor() {
        // When
        ContaminantsData data = new ContaminantsData();

        // Then - Validate that the object is not null
        assertNotNull(data);
    }
}
