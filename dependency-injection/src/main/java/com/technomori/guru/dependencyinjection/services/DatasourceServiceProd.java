package com.technomori.guru.dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class DatasourceServiceProd implements DatasourceService {

    @Override
    public String getDatasourceURL() {
        return baseDatasourceURL + "/prod";
    }

}
