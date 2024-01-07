package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.UsersRepositoryImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

public class UsersService {

    private UsersRepositoryImpl usersRepository;

    public UsersService(SqlSessionFactory sqlSessionFactory) {
        this.usersRepository = new UsersRepositoryImpl(sqlSessionFactory);
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
            // Log the exception or handle it based on your application's requirements
            e.printStackTrace();
            return false;
        }
    }
}
