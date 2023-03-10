package com.technomori.guru.beerstore.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.technomori.guru.beerstore.domain.Beer;

import io.micrometer.common.util.StringUtils;

@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
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

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("No Hammers On The Bar")
                .beerStyle("Wheat")
                .upc("0083783375213")
                .price(BigDecimal.valueOf(6.33))
                .quantityOnHand(1114)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Adjunct Trail")
                .beerStyle("Stout")
                .upc("8380495518610")
                .price(BigDecimal.valueOf(63.81))
                .quantityOnHand(3131)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Collection<Beer> listBeers() {
        return beerMap.values();
    }

    @Override
    public Beer getBeerById(String id) {
        return beerMap.get(UUID.fromString(id));
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        beer.setId(UUID.randomUUID());
        beer.setVersion(1);
        beer.setCreatedAt(LocalDateTime.now());
        beer.setUpdatedAt(LocalDateTime.now());
        beerMap.put(beer.getId(), beer);
        return beer;
    }

    @Override
    public Beer updateBeer(String id, Beer beer) {
        Beer existingBeer = beerMap.get(UUID.fromString(id));
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setUpdatedAt(LocalDateTime.now());
        return existingBeer;
    }

    @Override
    public Beer deleteBeer(String id) {
        return beerMap.remove(UUID.fromString(id));
    }

    @Override
    public Beer patchBeer(String id, Beer beer) {
        Beer existingBeer = beerMap.get(UUID.fromString(id));
        if (StringUtils.isNotBlank(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }
        if (StringUtils.isNotBlank(beer.getBeerStyle())) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }
        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }
        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }
        if (StringUtils.isNotBlank(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }
        existingBeer.setUpdatedAt(LocalDateTime.now());
        return existingBeer;
    }

}
