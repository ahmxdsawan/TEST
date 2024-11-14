package com.cis3760.eauchart.waterwise.mappers;

import com.cis3760.eauchart.waterwise.models.DTOs.ChemicalInfoDTO;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterChemicalProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChemicalsMapper {

    /**
     * Maps the wastewasterChemicalProjections to ChemicalInfoDTOs with MapStruct
     * @param wastewasterChemicalProjections List of stations from wastewater dataset
     * @return ChemicalInfoDTOs of readings from wastewater station
     */
    @Mapping(source="last_updated", target="lastUpdated")
    @Mapping(source="sample_date", target="sampleDate")
    @Mapping(source="measured_value", target="measuredValue")
    @Mapping(source="unit_of_measure", target="unitOfMeasure")
    List<ChemicalInfoDTO> wastewaterReadingProjectionToChemicalInfoDTO(List<WastewaterChemicalProjection> wastewasterChemicalProjections);


}