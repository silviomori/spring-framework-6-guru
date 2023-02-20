package com.technomori.guru.beerstore.services;

import java.util.Collection;

import com.technomori.guru.beerstore.domain.Beer;

public interface BeerService {

    Collection<Beer> listBeers();

    Beer getBeerById(String id);

    Beer saveNewBeer(Beer beer);

    Beer updateBeer(String id, Beer beer);

    Beer deleteBeer(String id);

}
