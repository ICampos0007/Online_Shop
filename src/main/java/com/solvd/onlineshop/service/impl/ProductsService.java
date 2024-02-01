package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.ProductsRepositoryImpl;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductsService {
    private static final Logger logger = LogManager.getLogger(ProductsService.class);
    private ProductsRepositoryImpl productsRepositoryImpl;

    public ProductsService(SqlSessionFactory sqlSessionFactory) {
        this.productsRepositoryImpl = new ProductsRepositoryImpl(sqlSessionFactory);
    }

    public void createProduct(Products product) {
        productsRepositoryImpl.createProduct(product);
        logger.info("Product created successfully: " + product.getId());
    }

    // Check if the product already exists
    public boolean isProductExists(int productId) throws SQLException {
        Products existingProduct = productsRepositoryImpl.getProductById(productId);
        return existingProduct != null;
    }

    // Example: Create a new product if it doesn't exist
    public void createProductIfNotExists(Products product) throws SQLException {
        if (!isProductExists(product.getId())) {
            createProduct(product);
        } else {
            logger.info("Product with ID " + product.getId() + " already exists.");
        }
    }
}
