package com.books.books.controller;

import com.books.books.Book;

import com.books.books.service.BookService;
import com.books.books.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BookService bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBooks(@RequestBody Book book){
         Book savedBook = bookService.create(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }



        @GetMapping(path = "/savedBooks/{id}")
        public ResponseEntity<Book> savedBook(@PathVariable Integer id){
        Optional<Book> foundBook=bookService.findById(id);
        if(foundBook.isPresent()){
            return new ResponseEntity<>(foundBook.get(),HttpStatus.OK);
        }
        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> allBooks(){
        return new ResponseEntity<>(bookService.listBooks(),HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delBook(@PathVariable Integer id){
        bookService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
