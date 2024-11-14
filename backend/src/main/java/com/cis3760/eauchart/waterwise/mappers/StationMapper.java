package com.cis3760.eauchart.waterwise.mappers;

import com.cis3760.eauchart.waterwise.models.DTOs.StationInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.projections.LakeWaterQualityStationProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.StreamWaterQualityStationProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterStationProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {

    /**
     * Maps the WastewaterStationProjections to StationInfoDTOs with MapStruct
     * @param wastewaterStationProjections List of stations from wastewater dataset
     * @return StationInfoDTOs of stations from wastewater dataset
     */
    @Mapping(source="last_updated", target="lastUpdated")
    @Mapping(source="works_name", target="worksName")
    @Mapping(source="company_code", target="companyCode")
    List<StationInfoDTO> wastewaterStationProjectionToStationInfoDTO(List<WastewaterStationProjection> wastewaterStationProjections);

    /**
     * Maps the LakeWaterQualityStationProjections to StationInfoDTOs with MapStruct
     * @param lakeWaterQualityStationProjections List of stations from Inland Lake Drinking Water Quality dataset
     * @return StationInfoDTOs of stations from lake water quality dataset
     */
    @Mapping(source="last_updated", target="lastUpdated")
    @Mapping(source="lake_name", target="waterBodyName")
    @Mapping(source="station_code", target="stationCode")
    @Mapping(source="station_details", target="stationDetails")
    List<StationInfoDTO> lakeWaterQualityStationProjectionToStationInfoDTO(List<LakeWaterQualityStationProjection> lakeWaterQualityStationProjections);
    default StationInfoDTO lakeWaterQualityStationProjectionToStationInfoDTO(LakeWaterQualityStationProjection projection) {
        StationInfoDTO dto = new StationInfoDTO();
        dto.setLastUpdated(projection.getLastUpdated());
        dto.setWaterBodyName(projection.getLakeName());
        dto.setStationCode(projection.getStationCode());
        dto.setStationDetails(projection.getStationDetails());
        return dto;
    }

    /**
     * Maps the StreamWaterQualityStationProjections to StationInfoDTOs with MapStruct
     * @param streamWaterQualityStationProjections List of stations from Stream Water Quality dataset
     * @return StationInfoDTOs of stations from stream water quality dataset
     */
    @Mapping(source = "lastUpdated", target = "lastUpdated")
    @Mapping(source = "stationDetails", target = "stationDetails")
    @Mapping(source = "streamName", target = "waterBodyName")
    List<StationInfoDTO> streamWaterQualityStationProjectionToStationInfoDTO(List<StreamWaterQualityStationProjection> streamWaterQualityStationProjections);

    default StationInfoDTO streamWaterQualityStationProjectionToStationInfoDTO(StreamWaterQualityStationProjection projection) {
        StationInfoDTO dto = new StationInfoDTO();
        dto.setLastUpdated(projection.getLastUpdated());
        dto.setStationDetails(projection.getStationDetails());
        dto.setWaterBodyName(projection.getStreamName());
        return dto;
    }
}
