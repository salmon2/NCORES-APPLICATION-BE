package com.ncores.plaluvs.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    @GetMapping("/")
    public String version() {
        return String.format("Project Version : %s", "0.0.1");
    }

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

}

