package com.technomori.guru.booklist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.technomori.guru.booklist.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
