package com.technomori.guru.beerstore.services;

import java.util.Collection;

import com.technomori.guru.beerstore.domain.Beer;

public interface BeerService {

    Collection<Beer> listBeers();

    Beer getBeerById(String id);

}
