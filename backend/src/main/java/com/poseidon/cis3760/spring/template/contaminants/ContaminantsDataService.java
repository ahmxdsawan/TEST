package com.poseidon.cis3760.spring.template.contaminants;

import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsMinMaxData;

public interface ContaminantsDataService {

    public ContaminantsData getContaminantsData(Integer id);

    public Iterable<ContaminantsMinMaxData> minmaxContaminantsData(String sector, String contaminant);

    public Iterable<ContaminantsData> allContaminantsData();

    public Integer count();
}