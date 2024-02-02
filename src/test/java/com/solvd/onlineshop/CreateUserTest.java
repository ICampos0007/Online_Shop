package com.solvd.onlineshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.ConnectionPool;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import com.solvd.onlineshop.service.UsersService;
import com.solvd.onlineshop.service.impl.UsersServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CreateUserTest {
    private UsersRepository usersRepository;
    private UsersService usersService;
    private Users newUser; // Store the newly created user

    @BeforeSuite
    public void setup() {
        Connection connection = ConnectionPool.getConnection();
        usersService = new UsersServiceImpl((SqlSessionFactory) usersRepository);
    }

    @Test(testName = "Create a new user", enabled = true)
    public void createNewUser() {
        newUser = new Users(0, "testUsername", "testPassW", "test@gmail.com");
        usersService.create(newUser);
    }

    @AfterSuite
    public void tearDown() {
        // Delete the newly created user from the JSON file
        if (newUser != null && newUser.getId() > 0) {
            deleteNewUserFromJson(newUser);
            System.out.println("User deleted successfully with ID: " + newUser.getId());
        } else {
            System.err.println("No user created to delete.");
        }

        ConnectionPool.closeAllConnections();
    }

    // Helper method to delete the new user from the JSON file
    private void deleteNewUserFromJson(Users userToDelete) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonData = new File("src/main/resources/Users.json");

        try {
            // Read existing users from JSON file
            List<Users> usersList = objectMapper.readValue(jsonData, new TypeReference<List<Users>>() {});

            // Remove the new user from the list
            usersList.removeIf(user -> user.getId() == userToDelete.getId());

            // Write the updated user list back to the JSON file
            objectMapper.writeValue(jsonData, usersList);
        } catch (IOException e) {
            // Log the exception or handle it based on your application's requirements
            e.printStackTrace();
        }
    }
}
