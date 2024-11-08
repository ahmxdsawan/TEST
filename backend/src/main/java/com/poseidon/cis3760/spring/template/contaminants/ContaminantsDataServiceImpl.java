package com.poseidon.cis3760.spring.template.contaminants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.cis3760.spring.template.contaminants.dao.ContaminantsDataDao;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsMinMaxData;

import java.util.Map;
import java.util.HashMap;

@Service
public class ContaminantsDataServiceImpl implements ContaminantsDataService {

    @Autowired
    private ContaminantsDataDao contaminantsDataDao;

    @Override
    public ContaminantsData getContaminantsData(Integer id) {
        return contaminantsDataDao.findById(id).get();
    }

    @Override
    public Iterable<ContaminantsMinMaxData> minmaxContaminantsData(String sector, String contaminant) {

        Iterable<ContaminantsData> data = contaminantsDataDao.getMinMaxContaminant(sector, contaminant);
        // use a map to combine the rows
        Map<String, ContaminantsMinMaxData> minMaxData = new HashMap<>();

        for (ContaminantsData row : data) {
            // if this specific date is not already in the map, make an entry
            if (!minMaxData.containsKey(row.getSampleDate())) {
                ContaminantsMinMaxData mmd = new ContaminantsMinMaxData(
                    row.getId(),
                    row.getSectorDisplayName(),
                    row.getFacilityName(),
                    row.getSampleDate(),
                    row.getContaminantDisplayName(),
                    row.getReportedAs(),
                    Double.NEGATIVE_INFINITY,
                    Double.NEGATIVE_INFINITY,
                    Double.NEGATIVE_INFINITY,
                    row.getUnits()
                );

                minMaxData.put(row.getSampleDate(), mmd);
            }

            // update the entry for this date
            if (row.getComponentType().equals("AVERAGE")) {
                minMaxData.get(row.getSampleDate()).setAverage(row.getValue());
            }
            else if (row.getComponentType().equals("MAXIMUM")) {
                minMaxData.get(row.getSampleDate()).setMaximum(row.getValue());
            }
            else if (row.getComponentType().equals("MINIMUM")) {
                minMaxData.get(row.getSampleDate()).setMinimum(row.getValue());
            }
        }
        
        return minMaxData.values();
    }

    @Override
    public Iterable<ContaminantsData> allContaminantsData() {
        return contaminantsDataDao.findAll();
    }

    @Override
    public Integer count() {
        return contaminantsDataDao.getCount();
    }
}