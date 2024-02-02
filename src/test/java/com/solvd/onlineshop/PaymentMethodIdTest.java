package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.PaymentMethods;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.PaymentMethodsRepository;
import com.solvd.onlineshop.service.PaymentMethodService;
import com.solvd.onlineshop.service.impl.PaymentMethodsImpl;
import java.util.Optional;

import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;

public class PaymentMethodIdTest {
    private PaymentMethodsRepository paymentMethodsRepository;
    private PaymentMethodService paymentMethodService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        paymentMethodService = new PaymentMethodsImpl( paymentMethodsRepository);
    }

    @DataProvider(name = "validId")
    public Object[][] validId() {
        return new Object[][] {
                {1}
        };
    }
    @Test(testName = "This test verifies if a Payment method with a given id exists.", dataProvider = "validId", enabled = true)
    public void verifyPaymentMethodIdTest(int id) {
        Optional<PaymentMethods> paymentMethodsId = paymentMethodService.findById(id);

        Assert.assertTrue(paymentMethodsId.isPresent(), "Payment method with ID" + id + "should be found.");
    }

    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }

}
