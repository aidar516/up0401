package com.example.demo.repository;

import com.example.demo.models.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends GenericRepository<Book, Long> { }
