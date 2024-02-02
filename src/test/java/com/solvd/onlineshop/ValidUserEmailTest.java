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

public class ValidUserEmailTest {
    private UsersRepository usersRepository;
    private UsersService usersService;
    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        usersService = new UsersServiceImpl((SqlSessionFactory) usersRepository);
    }
    @DataProvider
    public Object[][] validEmail() {
        return new Object[][] {
                {"jaymee@gmail.com"},
        };
    }

    @Test(testName = "This test verifies if a user with a given email exists.", dataProvider = "validEmail", enabled = true)
    public void verifyUsernameTest(String email) {
        Optional<Users> validEmail = usersService.findByEmail(email);

        Assert.assertTrue(validEmail.isPresent(), "Product with name " + email + " should be found.");
    }

    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }
}
