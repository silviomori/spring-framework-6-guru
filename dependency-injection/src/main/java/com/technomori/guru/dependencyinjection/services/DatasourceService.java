package com.technomori.guru.dependencyinjection.services;

public interface DatasourceService {

    final String baseDatasourceURL = "http://localhost:5432";

    String getDatasourceURL();

}
