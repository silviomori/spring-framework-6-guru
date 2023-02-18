package com.technomori.guru.booklist.service;

import com.technomori.guru.booklist.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();

}
