package com.technomori.guru.booklist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.technomori.guru.booklist.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
