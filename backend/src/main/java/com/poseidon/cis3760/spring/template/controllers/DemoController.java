package com.poseidon.cis3760.spring.template.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/demo")
public class DemoController {

    @GetMapping()
    public String getDemo() {
        return "ping!";
    }
}