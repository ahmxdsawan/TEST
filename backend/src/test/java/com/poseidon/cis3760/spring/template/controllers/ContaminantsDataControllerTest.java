package com.poseidon.cis3760.spring.template.controllers;

import com.poseidon.cis3760.spring.template.contaminants.ContaminantsDataService;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ContaminantsDataController.class)
public class ContaminantsDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaminantsDataService contaminantsDataService;

    @Test
    public void testGetContaminantsData() throws Exception {
        // Given
        ContaminantsData data = new ContaminantsData();
        data.setId(1);
        data.setSectorDisplayName("Industrial");
        when(contaminantsDataService.getContaminantsData(1)).thenReturn(data);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/contaminants/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sectorDisplayName").value("Industrial"));
    }

    @Test
    public void testAllContaminantsData() throws Exception {
        // Given
        ContaminantsData data1 = new ContaminantsData();
        data1.setId(1);
        data1.setSectorDisplayName("Commercial");
        ContaminantsData data2 = new ContaminantsData();
        data2.setId(2);
        data2.setSectorDisplayName("Residential");
        when(contaminantsDataService.allContaminantsData()).thenReturn(Arrays.asList(data1, data2));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/contaminants/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].sectorDisplayName").value("Commercial"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].sectorDisplayName").value("Residential"));
    }

    @Test
    public void testCount() throws Exception {
        // Given
        when(contaminantsDataService.count()).thenReturn(5);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data/contaminants/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}
