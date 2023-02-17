package com.technomori.guru.booklist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.technomori.guru.booklist.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
