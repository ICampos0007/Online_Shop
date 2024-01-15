package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Users;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class UsersRepositoryImpl  {
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(UsersRepositoryImpl.class);
    private final SqlSessionFactory sqlSessionFactory;


    public UsersRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Create a new user
    public void createUser(Users user) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "INSERT INTO users (id, username, passw, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.executeUpdate();
                logger.info("User created successfully: " + user.getUsername());
            }
        } catch (SQLException e) {
            logger.error("Error creating user: " + user.getUsername(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve a user by username
    public Users getUserByUsername(String username) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Users(
                                resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("passw"),
                                resultSet.getString("email")
                        );
                    }
                }
            }
        }
        return null; // Return null if no user found
    }
}
