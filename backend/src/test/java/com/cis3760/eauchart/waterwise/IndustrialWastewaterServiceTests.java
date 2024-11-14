package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.mappers.StationMapper;
import com.cis3760.eauchart.waterwise.mappers.ChemicalsMapper;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.ChemicalInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.WastewaterRepository;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterStationProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterChemicalProjection;
import com.cis3760.eauchart.waterwise.services.IndustrialWastewaterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@SpringBootTest
class IndustrialWastewaterServiceTests {
    @Mock
    private WastewaterRepository wastewaterRepository;
    @Mock
    private StationMapper stationMapper;
    @Mock
    private ChemicalsMapper chemicalMapper;

    @InjectMocks
    private IndustrialWastewaterServiceImpl industrialWastewaterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStationInfo() {
        // Setup
        List<StationInfoDTO> expectedStationInfoDTOs = new ArrayList<>();
        List<WastewaterStationProjection> expectedWastewaterStationProjections = new ArrayList<>();
        expectedStationInfoDTOs.add(getStationInfoDTO());
        expectedWastewaterStationProjections.add(getWastewaterStationProjection());

        when(wastewaterRepository.getStations())
                .thenReturn(expectedWastewaterStationProjections);
        when(stationMapper.wastewaterStationProjectionToStationInfoDTO(expectedWastewaterStationProjections))
                .thenReturn(expectedStationInfoDTOs);

        // Act
        List<StationInfoDTO> result = industrialWastewaterService.getStationInfo();

        // Assert
        assertNotNull(result);
        assertEquals(result, expectedStationInfoDTOs, "Wastewater StationInfoDTOs not fetched correctly: ");
        verify(wastewaterRepository, times(1)).getStations();
        verify(stationMapper, times(1)).wastewaterStationProjectionToStationInfoDTO(expectedWastewaterStationProjections);
    }

    @Test
    public void testChemicalReadingsDTO() {
        // Setup
        List<ChemicalInfoDTO> expectedChemicalInfoDTOs = new ArrayList<>();
        List<WastewaterChemicalProjection> expectedWastewaterChemicalProjections = new ArrayList<>();
        expectedChemicalInfoDTOs.add(getChemicalInfoDTO());
        expectedWastewaterChemicalProjections.add(getWastewaterChemicalProjection());

        when(wastewaterRepository.getChemicalReadings("2"))
                .thenReturn(expectedWastewaterChemicalProjections);
        when(chemicalMapper.wastewaterReadingProjectionToChemicalInfoDTO((expectedWastewaterChemicalProjections)))
                .thenReturn(expectedChemicalInfoDTOs);

        // Act
        List<ChemicalInfoDTO> result = industrialWastewaterService.getChemicalReadings("2");

        // Assert
        assertNotNull(result);
        assertEquals(result, expectedChemicalInfoDTOs, "Wastewater ChemicalInfoDTOs not fetched correctly: ");
        verify(wastewaterRepository, times(1)).getChemicalReadings("2");
        verify(chemicalMapper, times(1)).wastewaterReadingProjectionToChemicalInfoDTO(expectedWastewaterChemicalProjections);
    }

    /**
     * Creates a mock WastewaterStationProjection object to be used in tests
     * @return mock WastewaterStationProjection object
     */
    private static WastewaterStationProjection getWastewaterStationProjection() {
        return new WastewaterStationProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public LocalDateTime getLastUpdated() {
                return LocalDateTime.parse("2024-10-03T12:34:56");
            }

            @Override
            public String getSector() {
                return "ELECTRIC POWER GENERATION";
            }

            @Override
            public String getWorksName() {
                return "BRUCE POWER INC. - DEVELOPMENT SERVICES A AND B";
            }

            @Override
            public String getCompanyCode() {
                return "0001840107";
            }

            @Override
            public String getMunicipality() {
                return "KINCARDINE, MUNICIPALITY";
            }
        };
    }

    /**
     * Creates a mock WastewaterChemicalProjection object to be used in tests
     * @return mock WastewaterChemicalProjection object
     */
    private static WastewaterChemicalProjection getWastewaterChemicalProjection() {
        return new WastewaterChemicalProjection() {
            @Override
            public LocalDateTime getLastUpdated() {
                return LocalDateTime.parse("2024-10-07T12:34:56");
            }

            @Override
            public String getSampleDate() {
                return "2016/07";
            }

            @Override
            public BigDecimal getMeasuredValue() {
                return BigDecimal.valueOf(0.882);
            }

            @Override
            public String getUnitOfMeasure() {
                return "KG/D";
            }
        };
    }
    /**
     * Creates a mock StationInfoDTO
     * @return mock StationInfoDTO object
     */
    private static StationInfoDTO getStationInfoDTO() {
        return new StationInfoDTO(
            LocalDateTime.parse("2024-10-03T12:34:56"),
            "ELECTRIC POWER GENERATION",
            "BRUCE POWER INC. - DEVELOPMENT SERVICES A AND B",
            "0001840107",
            "KINCARDINE, MUNICIPALITY",
            null,
            null,
            null
        );
    }

    /**
     * Creates a mock ChemicalInfoDTO
     * @return mock ChemicalInfoDTO object
     */
    private static ChemicalInfoDTO getChemicalInfoDTO() {
        return new ChemicalInfoDTO(
            LocalDateTime.parse("2024-10-07T12:34:56"),
            "2016/07",
            BigDecimal.valueOf(0.882),
            "KG/D"
        );
    }
}
