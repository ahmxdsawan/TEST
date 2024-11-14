package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.mappers.StationMapper;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.LakeWaterQualityRepository;
import com.cis3760.eauchart.waterwise.repositories.projections.LakeWaterQualityStationProjection;
import com.cis3760.eauchart.waterwise.services.InlandLakeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@SpringBootTest
class InlandLakeServiceTests {
    @Mock
    private LakeWaterQualityRepository lakeWaterQualityRepository;
    @Mock
    private StationMapper stationMapper;

    @InjectMocks
    private InlandLakeServiceImpl inlandLakeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStationInfo() {
        // Setup
        List<StationInfoDTO> expectedStationInfoDTOs = new ArrayList<>();
        List<LakeWaterQualityStationProjection> expectedLakeWaterQualityStationProjections = new ArrayList<>();
        expectedStationInfoDTOs.add(getStationInfoDTO());
        expectedLakeWaterQualityStationProjections.add(getLakeWaterQualityStationProjection());

        when(lakeWaterQualityRepository.getStations())
                .thenReturn(expectedLakeWaterQualityStationProjections);
        when(stationMapper.lakeWaterQualityStationProjectionToStationInfoDTO(expectedLakeWaterQualityStationProjections))
                .thenReturn(expectedStationInfoDTOs);

        // Act
        List<StationInfoDTO> result = inlandLakeService.getStationInfo();

        // Assert
        assertNotNull(result);
        assertEquals(result, expectedStationInfoDTOs, "Station Info DTOs not fetched correctly: ");
        verify(lakeWaterQualityRepository, times(1)).getStations();
        verify(stationMapper, times(1)).lakeWaterQualityStationProjectionToStationInfoDTO(expectedLakeWaterQualityStationProjections);
    }

    /**
     * Creates a mock LakeWaterQualityStationProjection object to be used in tests
     * @return mock LakeWaterQualityStationProjection object
     */
    private static LakeWaterQualityStationProjection getLakeWaterQualityStationProjection() {
        return new LakeWaterQualityStationProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public LocalDateTime getLastUpdated() {
                return LocalDateTime.parse("2024-10-03T12:34:56");
            }

            @Override
            public String getLakeName() {
                return "Blue Chalk Lake";
            }

            @Override
            public String getStationCode() {
                return "BC";
            }

            @Override
            public String getStationDetails() {
                return "03007754001";
            }
        };
    }

    /**
     * Creates a mock StationInfoDTO to be used in tests
     * @return mock StationInfoDTO object
     */
    private static StationInfoDTO getStationInfoDTO() {
        return new StationInfoDTO(
            LocalDateTime.parse("2024-10-03T12:34:56"),
            null,
            null,
            null,
            null,
            null,
            null,
            "11000100502"
        );
    }

}
