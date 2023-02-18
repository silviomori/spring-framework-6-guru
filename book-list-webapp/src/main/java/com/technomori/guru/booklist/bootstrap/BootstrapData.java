package com.technomori.guru.booklist.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.technomori.guru.booklist.domain.Author;
import com.technomori.guru.booklist.domain.Book;
import com.technomori.guru.booklist.domain.Publisher;
import com.technomori.guru.booklist.repositories.AuthorRepository;
import com.technomori.guru.booklist.repositories.BookRepository;
import com.technomori.guru.booklist.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.save(
                new Book("Domain Driven Design", "978-0-132-18127-3",
                        List.of(new Author("Eric", "Evans")),
                        new Publisher("Addison-Wesley Professional", "501 Boylston St Ste 900", "Boston", "MA",
                                "02116")));
        bookRepository.save(
                new Book("J2EE Development without EJB", "978-0-764-57390-3",
                        List.of(new Author("Rod", "Johnson"), new Author("Jürgen", "Höller")),
                        new Publisher("Wiley", "111 River Street", "Hoboken", "NJ", "07030")));

        System.out.println("--- Bootstrap Data ---");
        authorRepository.findAll().forEach(System.out::println);
        bookRepository.findAll().forEach(System.out::println);
        publisherRepository.findAll().forEach(System.out::println);
    }

}
