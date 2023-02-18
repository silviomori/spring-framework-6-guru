package com.technomori.guru.booklist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.technomori.guru.booklist.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

}
