package com.books.books.repo;


import com.books.books.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<BooksEntity,Integer> {
    Optional<BooksEntity> findById(Integer id);
}
