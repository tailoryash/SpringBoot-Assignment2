package com.yashtailor.assignment2.service;

import com.yashtailor.assignment2.entity.*;

import java.util.*;

public interface BookService {
    public List<Book> getAllBooks();

    public void addBook(Book book);

    public Optional<Book> getBookById(long id);

    void deleteBook(long id);

    void updateBook(Book book, long id);
}
