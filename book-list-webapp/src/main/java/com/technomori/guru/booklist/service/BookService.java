package com.technomori.guru.booklist.service;

import com.technomori.guru.booklist.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
