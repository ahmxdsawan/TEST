package com.cis3760.eauchart.waterwise.repositories;

import com.cis3760.eauchart.waterwise.models.StreamWaterQuality;
import com.cis3760.eauchart.waterwise.repositories.projections.StreamWaterQualityReadingProjection;
import com.cis3760.eauchart.waterwise.repositories.projections.StreamWaterQualityStationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StreamWaterQualityRepository extends JpaRepository<StreamWaterQuality, Long> {
    @Query(value =
            """
                SELECT sq.lastUpdated as lastUpdated,
                       sq.stationDetails as stationDetails,
                       m.stream_name as streamName
                FROM (
                    SELECT MAX(last_updated) AS lastUpdated,
                           station AS stationDetails
                    FROM stream_water_quality
                    GROUP BY station
                ) sq
                JOIN stream_water_quality_monitoring m
                ON sq.stationDetails = m.station
            """,
            nativeQuery = true)
    List<StreamWaterQualityStationProjection> getStations();

    @Query(value =
            "SELECT DISTINCT\n" +
            "    last_updated as lastUpdated,\n" +
            "    parm_code as parameterCode,\n" +
            "    parm_description as parameterDescription,\n" +
            "    year,\n" +
            "    sample_date as date,\n" +
            "    sample_time as time,\n" +
            "    field_no as fieldNumber,\n" +
            "    remark_code as remarkCode,\n" +
            "    result,\n" +
            "    value_qualifier as valueQualifier,\n" +
            "    units,\n" +
            "    analysis_method as analysisMethod\n" +
            "FROM stream_water_quality\n" +
            "WHERE station = ?1",
            nativeQuery = true)
    List<StreamWaterQualityReadingProjection> getReadings(String stationCode);

    @Query(value = """
        SELECT 
            station AS stationId,
            CAST(DATE_FORMAT(sample_date, '%Y-%m-01') AS DATE) AS date, 
            parm_code AS parameterCode, 
            AVG(result) AS result
        FROM 
            stream_water_quality
        WHERE 
            station = :station
            AND YEAR(sample_date) = 2018 
            AND parm_code IN ('PH', 'CLIDUR', 'PPUT')
        GROUP BY 
            station, DATE_FORMAT(sample_date, '%Y-%m-01'), parm_code
        ORDER BY 
            station, date
        """, nativeQuery = true)
    List<StreamWaterQualityReadingProjection> getMonthlyAverageForStation(String station);


}
