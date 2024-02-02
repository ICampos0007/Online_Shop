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

public class UsersIdTest {
    private UsersRepository usersRepository;
    private UsersService usersService;

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        usersService = new UsersServiceImpl((SqlSessionFactory) usersRepository);
    }

    @DataProvider(name = "validId")
    public Object[][] validId() {
        return new Object[][] {
                {6}
        };
    }
    @Test(testName = "This test verifies if a address with a given id exists.", dataProvider = "validId", enabled = true)
    public void verifyUserIdTest(int id) {
        Optional<Users> userId = usersService.getById(id);

        Assert.assertTrue(userId.isPresent(), "user with ID" + id + "should be found.");

    }
    @AfterSuite
    public void tearDown() {
        ConnectionPool.closeAllConnections();
    }

}
