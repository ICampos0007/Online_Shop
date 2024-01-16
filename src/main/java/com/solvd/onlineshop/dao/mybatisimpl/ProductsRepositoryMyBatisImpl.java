package com.solvd.onlineshop.dao.mybatisimpl;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ProductsRepositoryMyBatisImpl implements ProductsRepository {
    private static final Logger logger = LogManager.getLogger(ProductsRepositoryMyBatisImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    public ProductsRepositoryMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void create(Products products) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            ProductsRepository productsRepository = sqlSession.getMapper(ProductsRepository.class);
            productsRepository.create(products);
            logger.info("Product created successfully: " + products.getName());
        }
    }

    @Override
    public Optional<Products> findByName(String name) {
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
            logger.info("Product id has been deleted" + id);
        }
    }
}
