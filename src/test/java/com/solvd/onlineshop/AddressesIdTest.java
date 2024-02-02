package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.AddressesRepository;
import com.solvd.onlineshop.service.AddressesService;
import com.solvd.onlineshop.service.impl.AddressesServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

public class AddressesIdTest {
    private AddressesRepository addressesRepository;
    private AddressesService addressesService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        addressesService = new AddressesServiceImpl((SqlSessionFactory) addressesRepository);
    }

    @DataProvider(name = "validId")
    public Object[][] validId() {
        return new Object[][] {
                {1}
        };
    }

    @Test(testName = "This test verifies if a address with a given id exists.", dataProvider = "validId", enabled = true)
    public void verifyAddressIdTest(int id) {
        Optional<Addresses> addressesId = addressesService.getById(id);

        Assert.assertTrue(addressesId.isPresent(), "Address with ID" + id + "should be found.");
    }

    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }

}
