package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.Products;

import java.util.Optional;

public interface ProductsRepository {
    void create(Products products);

    Optional<Products> findByName(String name);

    Optional<Products> findById(int id);

    void deleteById(int id);
}
