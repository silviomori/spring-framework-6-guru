package com.technomori.guru.beerstore.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public Beer getBeerById(@PathVariable String id) {
        return beerService.getBeerById(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@RequestBody Beer beer) {
        beerService.saveNewBeer(beer);
        return ResponseEntity
                .created(URI.create(
                        BeerController.class.getAnnotation(RequestMapping.class).value()[0] + "/"
                                + beer.getId().toString()))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBeer(@PathVariable String id, @RequestBody Beer beer) {
        beer = beerService.updateBeer(id, beer);
        return ResponseEntity.noContent().build();
    }
}
