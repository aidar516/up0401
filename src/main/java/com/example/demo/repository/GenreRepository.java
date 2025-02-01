package com.example.demo.repository;

import com.example.demo.models.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends GenericRepository<Genre, Long>{
}
