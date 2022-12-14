package com.codefarm.codefarmer.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VondaBusAlertApplication {
    public static void main(String[] args) {
        SpringApplication.run(VondaBusAlertApplication.class, args);
    }
}
