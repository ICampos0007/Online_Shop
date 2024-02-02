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

public class ProductNameTest {
    private ProductsRepository productsRepository;
    private ProductsService productsService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        productsService = new ProductsServiceImpl(productsRepository);
    }

    @DataProvider(name = "validName")
    public Object[][] validName() {
        return new Object[][] {
                {"IPhone"},
//                {"Sony headphones"}
        };
    }

    @Test(testName = "This test verifies if a product with a given name exists.", dataProvider = "validName", enabled = true)
    public void verifyProductNameTest(String name) {
        // Call the findByName method from the service
        Optional<Products> foundProduct = productsService.findByName(name);
        // Assert that the product is found (Optional is not empty)
        Assert.assertTrue(foundProduct.isPresent(), "Product with name " + name + " should be found.");
    }


    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }
}
