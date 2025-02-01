package com.example.demo.repository;

import com.example.demo.models.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order, Long>{
}
