package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.OrdersRepository;
import com.solvd.onlineshop.service.OrderService;
import com.solvd.onlineshop.service.impl.OrdersServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Optional;

import java.sql.Connection;

public class OrderIdTest {
    private OrdersRepository ordersRepository;
    private OrderService orderService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        orderService = new OrdersServiceImpl((SqlSessionFactory) ordersRepository);
    }

    @DataProvider(name = "validId")
    public Object[][] validId() {
        return new Object[][] {
                {1}
        };
    }

    @Test(testName = "This test verifies if a order with a given id exists.", dataProvider = "validId", enabled = true)
    public void verifyOrderId(int id) {
        Optional<Orders> validOrderId = orderService.findById(id);

        Assert.assertTrue(validOrderId.isPresent(), "Order with ID" + id + "should be found.");
    }


    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }

}
