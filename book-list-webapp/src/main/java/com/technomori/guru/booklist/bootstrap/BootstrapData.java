package com.technomori.guru.booklist.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.technomori.guru.booklist.domain.Author;
import com.technomori.guru.booklist.domain.Book;
import com.technomori.guru.booklist.repositories.AuthorRepository;
import com.technomori.guru.booklist.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.save(
                new Book("Domain Driven Design", "978-0-132-18127-3", List.of(new Author("Eric", "Evans"))));
        bookRepository.save(
                new Book("J2EE Development without EJB", "978-0-764-57390-3",
                        List.of(new Author("Rod", "Johnson"), new Author("Jürgen", "Höller"))));

        System.out.println("--- Bootstrap Data ---");
        authorRepository.findAll().forEach(System.out::println);
        bookRepository.findAll().forEach(System.out::println);
    }

}
