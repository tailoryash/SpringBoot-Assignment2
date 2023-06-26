package com.yashtailor.assignment2.service;

import com.yashtailor.assignment2.entity.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class BookServiceImpl implements BookService {
    static List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book(101, "Java Reference", "James"));
        bookList.add(new Book(102, "Java: The Complete Reference\n" +
                "Book by Herbert Schildt", "Herbert Schildt"));
        bookList.add(new Book(103, "Head first", "David"));
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public Optional<Book> getBookById(long id) {
        return bookList.stream().filter(b -> b.getBookId() == id).findFirst();
    }

    public void deleteBook(long id) {
        bookList = bookList.stream().filter(book -> book.getBookId() != id).collect(Collectors.toList());
    }

    public void updateBook(Book book, long id) {
        bookList = bookList.stream().map(b -> {
            if (b.getBookId() == id) {
                b.setBookName(book.getBookName());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    }
}
