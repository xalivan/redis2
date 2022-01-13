package com.example.redis3.controller;


import com.example.redis3.model.Book;
import com.example.redis3.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        long startTime = System.currentTimeMillis();
        List<Book> books = bookService.findAll();
        long endTime = System.currentTimeMillis() - startTime;
        log.info("Duration = {}", endTime);

        return ResponseEntity.status(HttpStatus.OK)
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.save(book));
    }

    public ResponseEntity delete(@PathVariable Long id) {
        bookService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
