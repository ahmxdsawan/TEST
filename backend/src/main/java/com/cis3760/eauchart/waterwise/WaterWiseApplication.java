package com.cis3760.eauchart.waterwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cis3760.eauchart.waterwise.repositories")
public class WaterWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterWiseApplication.class, args);
    }
}
