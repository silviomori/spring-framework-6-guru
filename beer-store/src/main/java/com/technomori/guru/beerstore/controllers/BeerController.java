package com.technomori.guru.beerstore.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technomori.guru.beerstore.domain.Beer;
import com.technomori.guru.beerstore.services.BeerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public Collection<Beer> listBeers() {
        return beerService.listBeers();
    }

}
