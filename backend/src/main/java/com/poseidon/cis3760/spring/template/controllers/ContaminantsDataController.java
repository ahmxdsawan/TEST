package com.poseidon.cis3760.spring.template.controllers;

import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsMinMaxData;
import com.poseidon.cis3760.spring.template.contaminants.ContaminantsDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(path = "/api/data/contaminants")
@CrossOrigin(origins = "http://localhost:3000") // frontend URL
public class ContaminantsDataController {
    private ContaminantsDataService contaminantsDataService;

    @Autowired
    ContaminantsDataController(ContaminantsDataService contaminantsDataService) {
        this.contaminantsDataService = contaminantsDataService;
    }

    @GetMapping("/get/{id}")
    private @ResponseBody ContaminantsData getContaminantsData(@PathVariable Integer id) {
        return contaminantsDataService.getContaminantsData(id);
    }

    @GetMapping("/{sector}/{contaminant}/minmax")
    private @ResponseBody Iterable<ContaminantsMinMaxData> minmaxContaminantsData(@PathVariable String sector,
            @PathVariable String contaminant) {
        return contaminantsDataService.minmaxContaminantsData(sector, contaminant);
    }

    @GetMapping("/all")
    private @ResponseBody Iterable<ContaminantsData> allContaminantsData() {
        return contaminantsDataService.allContaminantsData();
    }

    @GetMapping("/count")
    private @ResponseBody Integer count() {
        return contaminantsDataService.count();
    }
}
