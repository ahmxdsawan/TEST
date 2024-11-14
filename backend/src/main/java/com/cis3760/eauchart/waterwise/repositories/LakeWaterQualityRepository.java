package com.cis3760.eauchart.waterwise.repositories;

import com.cis3760.eauchart.waterwise.models.LakeWaterQuality;
import com.cis3760.eauchart.waterwise.repositories.projections.LakeWaterQualityReadingProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.LakeWaterQualityStationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LakeWaterQualityRepository extends JpaRepository<LakeWaterQuality, Long> {

    @Query(value =
                "SELECT DISTINCT\n" +
                "    MAX(last_updated) as lastUpdated,\n" +
                "    lake_name as lakeName,\n" +
                "    station_code as stationCode,\n" +
                "    station_details as stationDetails\n" +
                "FROM inland_lake_drinking_water_quality\n" +
                "GROUP BY station_details",
            nativeQuery = true)
    List<LakeWaterQualityStationProjection> getStations();

    @Query(value =
            "SELECT DISTINCT\n" +
            "    last_updated as lastUpdated,\n" +
            "    sample_date as sampleDate,\n" +
            "    gran_alkalinity as granAlkalinity,\n" +
            "    calcium,\n" +
            "    chloride, \n" +
            "    specific_conductance as specificConductance, \n" +
            "    dic, \n" +
            "    doc, \n" +
            "    iron, \n" +
            "    potassium, \n" +
            "    magnesium, \n" +
            "    sodium, \n" +
            "    ammonium_ammonia as ammoniumAmmonia, \n" +
            "    nitrate_nitrite as nitrateNitrite, \n" +
            "    tkn, \n" +
            "    tn, \n" +
            "    ph, \n" +
            "    total_phosphorus as totalPhosphorus, \n" +
            "    silicate, \n" +
            "    sulfate \n" +
            "FROM inland_lake_drinking_water_quality\n" +
            "WHERE station_details = ?1",
            nativeQuery = true)
    List<LakeWaterQualityReadingProjection> getReadings(String stationCode);

    @Query(value =
            "SELECT sample_date AS sampleDate, lake_name AS lakeName, " +
            "ph, chloride, total_phosphorus AS totalPhosphorus, sulfate, " +
            "(COALESCE(CAST(ph AS DECIMAL(10, 2)), 0) + COALESCE(CAST(chloride AS DECIMAL(10, 2)), 0) + " +
            "COALESCE(CAST(total_phosphorus AS DECIMAL(10, 2)), 0) + COALESCE(CAST(sulfate AS DECIMAL(10, 2)), 0)) AS toxicityScore " +
            "FROM inland_lake_drinking_water_quality " +
            "WHERE lake_name = ?1 " +
            "ORDER BY sample_date DESC " +
            "LIMIT 1", // Returns only the latest reading
            nativeQuery = true)
    List<LakeWaterQualityReadingProjection> getWaterQualityByLake(String lakeName);

}

