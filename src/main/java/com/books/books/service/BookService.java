package com.books.books.service;

import com.books.books.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book create(Book book);

    Optional<Book> findById(Integer id);

    Boolean isBookExist(Book book);

    List<Book> listBooks();

    void deleteById(Integer id);
}
