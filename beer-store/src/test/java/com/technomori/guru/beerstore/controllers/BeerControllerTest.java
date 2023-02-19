package com.technomori.guru.beerstore.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void testGetBeerById() {
        log.info(beerController.listBeers().toString());
    }

}
