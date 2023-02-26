package com.technomori.guru.beerstore.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.technomori.guru.beerstore.domain.Beer;
import com.technomori.guru.beerstore.services.BeerService;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        Beer beerTest = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Pliny the Younger")
                .beerStyle("IPA")
                .upc("8484957731774")
                .price(BigDecimal.valueOf(51.37))
                .quantityOnHand(1122)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        given(beerService.getBeerById(any(String.class))).willReturn(beerTest);

        mockMvc.perform(
                get("/api/v1/beers/" + beerTest.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(beerTest.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(beerTest.getBeerName())));
    }

}
