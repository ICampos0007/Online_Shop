package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import com.solvd.onlineshop.service.UsersService;
import com.solvd.onlineshop.service.impl.UsersServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

public class ValidUsernameTest {
    private UsersRepository usersRepository;
    private UsersService usersService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        usersService = new UsersServiceImpl((SqlSessionFactory) usersRepository);
    }
    @DataProvider
    public Object[][] validUsername() {
        return new Object[][] {
                {"Nora"},
        };
    }
    @Test(testName = "This test verifies if a user with a given username exists.", dataProvider = "validUsername", enabled = true)
    public void verifyUsernameTest(String username) {
        Optional<Users> validUsername = usersService.findByUsername(username);

        Assert.assertTrue(validUsername.isPresent(), "Product with name " + username + " should be found.");
    }

    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }
}
