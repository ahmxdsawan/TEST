package com.poseidon.cis3760.spring.template.contaminants.dao;

import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContaminantsDataDao extends CrudRepository<ContaminantsData, Integer> {

    @Query("select count(*) from ContaminantsData")
    Integer getCount();

    @Query(value = "select * from wastewater_samples where sector_route_name = ?1 and contaminant_route_name = ?2", nativeQuery = true)
    Iterable<ContaminantsData> getMinMaxContaminant(String sector, String contaminant);
}
