package com.shaon.declarative_crud.repository;

import com.shaon.declarative_crud.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {
}
