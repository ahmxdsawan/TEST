package com.poseidon.cis3760.spring.template.controllers;

import com.poseidon.cis3760.spring.template.sewage.SewageService;
import com.poseidon.cis3760.spring.template.sewage.models.SewageData;
import com.poseidon.cis3760.spring.template.sewage.models.ContaminantExceedances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(path = "/api/data/sewage")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the frontend
public class SewageDataController {
    private SewageService sewageService;

    @Autowired
    SewageDataController(SewageService sewageService) {
        this.sewageService = sewageService;
    }

    @GetMapping("/get/{id}")
    private @ResponseBody SewageData getNote(@PathVariable Integer id) {
        return sewageService.getSewageData(id);
    }

    @GetMapping("/all")
    private @ResponseBody Iterable<SewageData> allNotes() {
        return sewageService.allNotes();
    }

    @GetMapping("/count")
    private @ResponseBody Integer count() {
        return sewageService.count();
    }

    @GetMapping("/{sector}/allcontaminants")
    private @ResponseBody Iterable<ContaminantExceedances> contaminantExceedances(@PathVariable String sector) {
        return sewageService.contaminantExceedances(sector);
    }

    @GetMapping("/{sector}/{contaminant}")
    private @ResponseBody Iterable<SewageData> sectorContaminant(@PathVariable String sector, @PathVariable String contaminant) {
        return sewageService.sectorContaminant(sector, contaminant);
    }
}

