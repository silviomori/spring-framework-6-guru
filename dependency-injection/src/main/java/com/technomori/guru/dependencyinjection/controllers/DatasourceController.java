package com.technomori.guru.dependencyinjection.controllers;

import org.springframework.stereotype.Controller;

import com.technomori.guru.dependencyinjection.services.DatasourceService;

@Controller
public class DatasourceController {

    private final DatasourceService datasourceService;

    public DatasourceController(DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    public String getDatasourceURL() {
        return datasourceService.getDatasourceURL();
    }

}
