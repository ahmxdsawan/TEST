package com.cis3760.eauchart.waterwise;

import com.cis3760.eauchart.waterwise.mappers.StationMapper;
import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.StreamWaterQualityRepository;
import com.cis3760.eauchart.waterwise.repositories.projections.StreamWaterQualityStationProjection;
import com.cis3760.eauchart.waterwise.services.StreamServiceImpl;
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
class StreamServiceTests {
    @Mock
    private StreamWaterQualityRepository streamWaterQualityRepository;
    @Mock
    private StationMapper stationMapper;

    @InjectMocks
    private StreamServiceImpl streamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStationInfo_() {
        // Setup
        List<StationInfoDTO> expectedStationInfoDTOs = new ArrayList<>();
        List<StreamWaterQualityStationProjection> expectedStreamWaterQualityStationProjections = new ArrayList<>();
        expectedStationInfoDTOs.add(getStationInfoDTO());
        expectedStreamWaterQualityStationProjections.add(getStreamWaterQualityStationProjection());

        when(streamWaterQualityRepository.getStations())
                .thenReturn(expectedStreamWaterQualityStationProjections);
        when(stationMapper.streamWaterQualityStationProjectionToStationInfoDTO(expectedStreamWaterQualityStationProjections))
                .thenReturn(expectedStationInfoDTOs);

        // Act
        List<StationInfoDTO> result = streamService.getStationInfo();

        // Assert
        assertNotNull(result);
        assertEquals(result, expectedStationInfoDTOs, "Station Info DTOs not fetched correctly: ");
        verify(streamWaterQualityRepository, times(1)).getStations();
        verify(stationMapper, times(1)).streamWaterQualityStationProjectionToStationInfoDTO(expectedStreamWaterQualityStationProjections);
    }

    /**
     * Creates a mock StreamWaterQualityStationProjection object to be used in tests
     * @return mock StreamWaterQualityStationProjection object
     */
    private static StreamWaterQualityStationProjection getStreamWaterQualityStationProjection() {
        return new StreamWaterQualityStationProjection() {
            @Override
            public LocalDateTime getLastUpdated() {
                return LocalDateTime.parse("2024-10-03T12:34:56");
            }

            @Override
            public String getStationDetails() {
                return "11000100502";
            }

            @Override
            public String getStreamName() {
                return "Lake Superior";
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
            null,
            null,
            null,
            null,
            "Blue Chalk Lake",
            "BC",
            "03007754001"
        );
    }
}
