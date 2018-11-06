package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("newBook", new Book());
        model.addAttribute("currentBook", new Book());
        return "books";
    }

    @GetMapping("/{id}")
    public String getBookById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("newBook", new Book());
        model.addAttribute("currentBook", bookService.getBookById(id));
        return "books";
    }

    @PostMapping
    public String addBook(Model model, @ModelAttribute Book book) {
        log.info("Add new book: {}", book);
        bookService.save(book);
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("newBook", new Book());
        model.addAttribute("currentBook", new Book());
        return "books";
    }

    @GetMapping("/rest")
    @ResponseBody
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }
}
