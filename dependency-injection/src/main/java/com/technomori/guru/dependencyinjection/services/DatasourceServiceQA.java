package com.technomori.guru.dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class DatasourceServiceQA implements DatasourceService {

    @Override
    public String getDatasourceURL() {
        return baseDatasourceURL + "/qa";
    }

}
