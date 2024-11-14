package com.cis3760.eauchart.waterwise.repositories;

import com.cis3760.eauchart.waterwise.models.Wastewater;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterStationProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterReadingProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.WastewaterChemicalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WastewaterRepository extends JpaRepository<Wastewater, Long> {

    @Query(value =
                "SELECT DISTINCT\n" +
                "    MAX(last_updated) as lastUpdated,\n" +
                "    sector,\n" +
                "    works_name as worksName,\n" +
                "    company_code as companyCode,\n" +
                "    municipality\n" +
                "FROM industrial_wastewater_by_facility\n" +
                "GROUP BY company_code",
            nativeQuery = true)
    List<WastewaterStationProjection> getStations();

    @Query(value =
                "SELECT DISTINCT \n" +
                "  last_updated as lastUpdated, \n" +
                "  sample_date as sampleDate, \n" +
                "  control_point_name as controlPointName, \n" +
                "  parameter_name as parameterName,\n" +
                "  frequency,\n" +
                "  component_type as componentType,\n" +
                "  measured_value as measuredValue,\n" +
                "  unit_of_measure as unitOfMeasure\n" +
                "FROM industrial_wastewater_by_facility\n" +
                "WHERE company_code = ?1",
            nativeQuery = true)
    List<WastewaterReadingProjection> getReadings(String companyCode);

    @Query(value =
                "SELECT DISTINCT \n" +
                "  last_updated as lastUpdated, \n" +
                "  sample_date as sampleDate, \n" +
                "  measured_value as measuredValue,\n" +
                "  unit_of_measure as unitOfMeasure\n" +
                "FROM industrial_wastewater_by_facility\n" +
                "WHERE company_code = ?1 AND parameter_name = 'PHOSPHORUS,UNFILTERED TOTAL'",
            nativeQuery = true)
    List<WastewaterChemicalProjection> getChemicalReadings(String stationCode);
}
