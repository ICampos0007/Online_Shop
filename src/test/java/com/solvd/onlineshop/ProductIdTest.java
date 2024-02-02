package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import com.solvd.onlineshop.service.ProductsService;
import com.solvd.onlineshop.service.impl.ProductsServiceImpl;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

public class ProductIdTest {
    private ProductsRepository productsRepository;
    private ProductsService productsService;
    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        productsService = new ProductsServiceImpl(productsRepository);
    }

    @DataProvider(name = "validId")
    public Object[][] validId() {
        return new Object[][] {
                {1}
        };
    }

    @Test(testName = "This test verifies if a product with a given id exists.", dataProvider = "validId", enabled = true)
    public void verifyProductIdTest(int id) {
        Optional<Products> productId = productsService.findById(id);

        Assert.assertTrue(productId.isPresent(), "Product with ID" + id + "should be found.");

    }

    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }

}
