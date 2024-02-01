package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Products;

import java.util.Optional;

public interface ProductsService {
    void create(Products products);

    Optional<Products> findByName(String name);

    Optional<Products> findById(int id);

    void deleteById(int id);
}
