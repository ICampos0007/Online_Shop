package com.solvd.onlineshop.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.UsersRepositoryImpl;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import com.solvd.onlineshop.service.UsersService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private UsersRepositoryImpl usersRepository;

    public UsersServiceImpl(SqlSessionFactory sqlSessionFactory) {
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

    @Override
    public int create(Users newUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonData = new File("src/main/resources/Users.json");

        try {
            // Read existing users from JSON file
            List<Users> usersList = objectMapper.readValue(jsonData, new TypeReference<List<Users>>() {});

            // Generate a unique ID for the new user
            int newUserId = generateUniqueId(usersList);

            // Set the ID for the new user
            newUser.setId(newUserId);

            // Add the new user to the list
            usersList.add(newUser);

            // Write the updated user list back to the JSON file
            objectMapper.writeValue(jsonData, usersList);
        } catch (IOException e) {
            // Log the exception or handle it based on your application's requirements
            e.printStackTrace();
        }
        return newUser.getId();
    }

    @Override
    public Optional<Users> getById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            return  usersRepository.findById(id);
        }
    }

    @Override
    public void updateById(Users users, int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            usersRepository.updateById(users, id);
        }
    }


    @Override
    public void delete(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            usersRepository.deleteById(id);
        }

    }
    // Helper method to generate a unique ID for the new user
    private int generateUniqueId(List<Users> usersList) {
        int maxId = usersList.stream().mapToInt(Users::getId).max().orElse(0);
        return maxId + 1;
    }
}
