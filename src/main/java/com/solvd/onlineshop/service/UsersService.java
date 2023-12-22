package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.UsersRepositoryImpl;

import java.sql.SQLException;

public class UsersService {

    private UsersRepositoryImpl usersRepository;

    public UsersService() {
        this.usersRepository = new UsersRepositoryImpl();
    }

    // Create a new user
    public void createUser(Users user) {
        usersRepository.createUser(user);
    }

    // Check if the username already exists
    public boolean isUsernameExists(String username) {
        try {
            Users existingUser = usersRepository.getUserByUsername(username);
            return existingUser != null;
        } catch (SQLException e) {
            // Handle the exception (e.g., log it) if there is an issue with the database query
            return false;
        }
    }
}
