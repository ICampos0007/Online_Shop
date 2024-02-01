package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import com.solvd.onlineshop.service.ProductsService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ProductsServiceImpl implements ProductsService {

    private static final Logger logger = LogManager.getLogger(ProductsServiceImpl.class);

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

//    public void createProduct(Products products) {
//        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
//            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
//
//            // Check if a product with the same name already exists
//            Optional<Products> existingProduct = productsRepository.findByName(products.getName());
//
//            if (existingProduct.isPresent()) {
//                // Product with the same name already exists, log an error
//                logger.error("Product with name {} already exists. Cannot create duplicate.", products.getName());
//            } else {
//                // Product with the same name does not exist, proceed with creation
//                productsRepository.create(products);
//                logger.info("Product created successfully: " + products.getName());
//            }
//        }
//    }

    @Override
    public void create(Products products) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
            productsRepository.create(products);

        }

    }

    public Optional <Products> findByName(String name) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
            return productsRepository.findByName(name);
        }
    }

    @Override
    public Optional<Products> findById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
            return productsRepository.findById(id);
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
            productsRepository.deleteById(id);
        }

    }

//    public void deleteProduct(int id) {
//        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
//            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
//
//            if (productsRepository.findById(id).isPresent()) {
//                // Product exists, proceed with deletion
//                productsRepository.deleteById(id);
//            } else {
//                // Product with the specified ID not found, log an error
//                logger.error("Cannot delete product with ID {}. Product not found", id);
//            }
//        }
//    }
}
