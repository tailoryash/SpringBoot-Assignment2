package com.yashtailor.assignment2.controller;

import com.yashtailor.assignment2.entity.*;
import com.yashtailor.assignment2.exception.*;
import com.yashtailor.assignment2.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(allBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> bookById = bookService.getBookById(id);
        if (bookById.isPresent()) {
            return ResponseEntity.ok(bookById.get());
        } else {
           throw new UserNotFoundException();
        }
//        return bookById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/books/add")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("books/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Deleted Id Found", HttpStatus.OK);
    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<List<Book>> updateBook(@RequestBody Book book, @PathVariable long id) {
        bookService.updateBook(book, id);

        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);

    }
}
