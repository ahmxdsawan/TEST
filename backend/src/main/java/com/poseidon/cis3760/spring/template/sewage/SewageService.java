package com.poseidon.cis3760.spring.template.sewage;

import com.poseidon.cis3760.spring.template.sewage.models.SewageData;
import com.poseidon.cis3760.spring.template.sewage.models.ContaminantExceedances;

public interface SewageService {

    public SewageData getSewageData(Integer id);

    public Iterable<SewageData> allNotes();

    public Integer count();

    public Iterable<ContaminantExceedances> contaminantExceedances(String sector);

    public Iterable<SewageData> sectorContaminant(String sector, String contaminant);
}
