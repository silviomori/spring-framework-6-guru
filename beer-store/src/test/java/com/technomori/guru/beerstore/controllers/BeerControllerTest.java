package com.technomori.guru.beerstore.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technomori.guru.beerstore.domain.Beer;
import com.technomori.guru.beerstore.services.BeerService;
import com.technomori.guru.beerstore.services.BeerServiceImpl;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    void testListBeers() throws Exception {
        Collection<Beer> listBeers = new BeerServiceImpl().listBeers();
        given(beerService.listBeers()).willReturn(listBeers);

        mockMvc.perform(get("/api/v1/beers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(listBeers.size())));
    }

    @Test
    void saveNewBeer() throws Exception {
        Beer newBeer = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Java Jill")
                .beerStyle("LAGER")
                .upc("4006016803570")
                .price(BigDecimal.valueOf(73.68))
                .quantityOnHand(2261)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(newBeer);

        mockMvc.perform(
                post("/api/v1/beers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBeer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateBeer() throws Exception {
        Beer beer = (Beer) new BeerServiceImpl().listBeers().toArray()[0];

        mockMvc.perform(
                put("/api/v1/beers/{}", beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());

        verify(beerService, times(1)).updateBeer(any(String.class), any(Beer.class));
    }

}
