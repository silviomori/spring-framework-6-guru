package com.technomori.guru.booklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technomori.guru.booklist.domain.Book;
import com.technomori.guru.booklist.repositories.BookRepository;
import com.technomori.guru.booklist.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}
