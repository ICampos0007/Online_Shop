package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import com.solvd.onlineshop.service.ProductsService;
import com.solvd.onlineshop.service.impl.ProductsServiceImpl;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class CreateProductTest {
    private static final Logger logger = LogManager.getLogger(CreateProductTest.class);
    private ProductsRepository productsRepository;
    private ProductsService productsService;
    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        productsService = new ProductsServiceImpl(productsRepository);
    }

    @Test(testName = "Create a new product", enabled = true)
    public void createNewProductTest() {
        Products products = new Products(26,"Fan", "high quality AC",150);
        productsService.create(products);
    }

    @AfterSuite
    public void tearDown() {
        // ID of the product to delete
        int productIdToDelete = 26;

        // Delete the product by its ID
        try {
            productsService.deleteById(productIdToDelete);
            logger.info("Product deleted successfully with ID: {}", productIdToDelete);
        } catch (Exception e) {
            logger.info("Error deleting product with ID: " + productIdToDelete);
            e.printStackTrace();
        }

        ConnectionPool.closeAllConnections();
    }
}
