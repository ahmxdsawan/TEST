package com.poseidon.cis3760.spring.template.sewage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.cis3760.spring.template.sewage.dao.SewageDataDao;
import com.poseidon.cis3760.spring.template.sewage.models.ContaminantExceedances;
import com.poseidon.cis3760.spring.template.sewage.models.SewageData;

@Service
public class SewageServiceImpl implements SewageService {

    @Autowired
    private SewageDataDao sewageDataDao;

    @Override
    public SewageData getSewageData(Integer id) {
        return sewageDataDao.findById(id).get();
    }

    @Override
    public Iterable<SewageData> allNotes() {
        return sewageDataDao.findAll();
    }

    @Override
    public Integer count() {
        return sewageDataDao.getCount();
    }

    @Override
    public Iterable<ContaminantExceedances> contaminantExceedances(String sector) {
        return sewageDataDao.getContaminantExceedances(sector);
    }

    @Override
    public Iterable<SewageData> sectorContaminant(String sector, String contaminant) {
        return sewageDataDao.getSectorContaminant(sector, contaminant);
    }

}
