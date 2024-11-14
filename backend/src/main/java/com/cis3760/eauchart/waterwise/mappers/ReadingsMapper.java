package com.cis3760.eauchart.waterwise.mappers;

import com.cis3760.eauchart.waterwise.models.DTOs.ReadingInfoDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.WaterQualityDTO;
import com.cis3760.eauchart.waterwise.models.DTOs.StreamWaterQualityDTO;
import com.cis3760.eauchart.waterwise.repositories.projections.LakeWaterQualityReadingProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.StreamWaterQualityReadingProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterReadingProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReadingsMapper {

    /**
     * Maps the WastewaterReadingsProjections to ReadingInfoDTOs with MapStruct
     * @param wastewaterReadingsProjections List of stations from wastewater dataset
     * @return ReadingInfoDTOs of readings from wastewater station
     */
    @Mapping(source="last_updated", target="lastUpdated")
    @Mapping(source="works_name", target="worksName")
    @Mapping(source="company_code", target="companyCode")
    @Mapping(source="sample_date", target="sampleDate")
    @Mapping(source="control_point_name", target="controlPointName")
    @Mapping(source="parameter_name", target="parameterName")
    @Mapping(source="component_type", target="componentType")
    @Mapping(source="measured_value", target="measuredValue")
    @Mapping(source="unit_of_measure", target="unitOfMeasure")
    List<ReadingInfoDTO> wastewaterReadingProjectionToReadingInfoDTO(List<WastewaterReadingProjection> wastewaterReadingsProjections);

    List<ReadingInfoDTO> lakeWaterQualityReadingProjectionToReadingInfoDTO(List<LakeWaterQualityReadingProjection> lakeWaterQualityReadingProjections);

    List<ReadingInfoDTO> streamWaterQualityReadingProjectionToReadingInfoDTO(List<StreamWaterQualityReadingProjection> streamWaterQualityReadingProjections);

    @Mapping(target = "lakeName", source = "lakeName")
    @Mapping(target = "sampleDate", source = "sampleDate")
    @Mapping(target = "chloride", source = "chloride")
    @Mapping(target = "sulfate", source = "sulfate")
    @Mapping(target = "pH", source = "pH")
    @Mapping(target = "totalPhosphorus", source = "totalPhosphorus")
    @Mapping(target = "toxicityScore", source = "toxicityScore")
    List<WaterQualityDTO> lakeWaterQualityReadingProjectionToWaterQualityDTO(List<LakeWaterQualityReadingProjection> projections);

    @Mapping(target = "parameterCode", source = "parameterCode")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "result", source = "result")
    List<StreamWaterQualityDTO> streamWaterQualityReadingProjectionToStreamWaterQualityDTO(List<StreamWaterQualityReadingProjection> streamWaterQualityReadingProjections);
}
