package com.technomori.guru.dependencyinjection.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("qa")
class DatasourceControllerQATest {

    @Test
    void testQAProfile(@Autowired DatasourceController controller) {
        String datasourceURL = controller.getDatasourceURL();
        System.out.println("DatasourceURL: " + datasourceURL);
        assertTrue(datasourceURL.endsWith("qa"));
    }

}
