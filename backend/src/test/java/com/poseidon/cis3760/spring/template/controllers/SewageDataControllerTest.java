package com.poseidon.cis3760.spring.template.controllers;

import com.poseidon.cis3760.spring.template.sewage.SewageService;
import com.poseidon.cis3760.spring.template.sewage.models.SewageData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SewageDataController.class)
public class SewageDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SewageService sewageService;

    @Test
    public void testGetNote() throws Exception {
        // Given
        SewageData data = new SewageData();
        data.setId(1);
        data.setSectorDisplayName("Wastewater");
        when(sewageService.getSewageData(1)).thenReturn(data);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/sewage/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sectorDisplayName").value("Wastewater"));
    }

    @Test
    public void testAllNotes() throws Exception {
        // Given
        SewageData data1 = new SewageData();
        data1.setId(1);
        data1.setSectorDisplayName("Industrial");
        SewageData data2 = new SewageData();
        data2.setId(2);
        data2.setSectorDisplayName("Residential");
        when(sewageService.allNotes()).thenReturn(Arrays.asList(data1, data2));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/sewage/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].sectorDisplayName").value("Industrial"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].sectorDisplayName").value("Residential"));
    }

    @Test
    public void testCount() throws Exception {
        // Given
        when(sewageService.count()).thenReturn(7);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/sewage/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("7"));
    }

    // Test the sector and contaminant endpoint
    @Test
    public void testSectorContaminant() throws Exception {
        // Given
        SewageData data1 = new SewageData();
        data1.setId(1);
        data1.setSectorDisplayName("Industrial");
        data1.setContaminantDisplayName("Ammonia");

        SewageData data2 = new SewageData();
        data2.setId(2);
        data2.setSectorDisplayName("Industrial");
        data2.setContaminantDisplayName("Ammonia");

        when(sewageService.sectorContaminant("Industrial", "Ammonia")).thenReturn(Arrays.asList(data1, data2));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/sewage/Industrial/Ammonia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].sectorDisplayName").value("Industrial"))
                .andExpect(jsonPath("$[0].contaminantDisplayName").value("Ammonia"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].sectorDisplayName").value("Industrial"))
                .andExpect(jsonPath("$[1].contaminantDisplayName").value("Ammonia"));
    }
}
