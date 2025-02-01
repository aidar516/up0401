package com.example.demo.repository;

import com.example.demo.models.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends GenericRepository<Publisher, Long> {
}
