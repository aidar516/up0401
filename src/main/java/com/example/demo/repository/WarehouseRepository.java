package com.example.demo.repository;

import com.example.demo.models.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends GenericRepository<Warehouse, Long>{
    List<Warehouse> findByBookId(Long bookId);
}
