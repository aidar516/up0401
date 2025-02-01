package com.example.demo.repository;

import com.example.demo.models.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends GenericRepository<Author, Long> {
}
