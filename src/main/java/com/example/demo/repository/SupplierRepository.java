package com.example.demo.repository;

import com.example.demo.models.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends GenericRepository<Supplier, Long> {
}
