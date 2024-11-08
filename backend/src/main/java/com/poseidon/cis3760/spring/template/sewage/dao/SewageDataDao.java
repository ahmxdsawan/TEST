package com.poseidon.cis3760.spring.template.sewage.dao;

import com.poseidon.cis3760.spring.template.sewage.models.SewageData;
import com.poseidon.cis3760.spring.template.sewage.models.ContaminantExceedances;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SewageDataDao extends CrudRepository<SewageData, Integer> {

    @Query("select count(*) from SewageData")
    Integer getCount();

    @Query(value="select sum(num_exceedances) as exceedances, contaminant_display_name as contaminant from sewage_data where sector_route_name = ?1 group by contaminant_display_name", nativeQuery=true)
    Iterable<ContaminantExceedances> getContaminantExceedances(String sector);

    @Query(value="select * from sewage_data where sector_route_name = ?1 and contaminant_route_name = ?2", nativeQuery=true)
    Iterable<SewageData> getSectorContaminant(String sector, String contaminant);

}
