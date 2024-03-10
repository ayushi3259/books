package com.books.books.service;

import com.books.books.Book;
import com.books.books.BooksEntity;
import com.books.books.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book create( Book book) {
        BooksEntity booksEntity=booktobookEntity(book);
        BooksEntity savedBook=bookRepo.save(booksEntity);
        return bookEntityToBook(savedBook);
    }

    public BooksEntity booktobookEntity(Book book){
        return BooksEntity.builder()
                .author(book.getAuthor())
                .bookName(book.getBookName())
                .build();
    }

    public Book bookEntityToBook(BooksEntity booksEntity){
        return Book.builder()
                .id(booksEntity.getId())
                .author(booksEntity.getAuthor())
                .bookName(booksEntity.getBookName())
                .build();
    }

    public Optional<Book> findById(Integer id){
        Optional<BooksEntity> foundBook= bookRepo.findById(id);
        return foundBook.map(book-> bookEntityToBook(book));
    }

    @Override
    public Boolean isBookExist(Book book) {
        return bookRepo.existsById(book.getId());
    }

    @Override
    public List<Book> listBooks() {
        List<BooksEntity> allBooks= bookRepo.findAll();
        return allBooks.stream().map(book->bookEntityToBook(book)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        bookRepo.deleteById(id);
    }

}
