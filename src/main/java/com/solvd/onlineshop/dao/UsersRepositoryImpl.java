package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Users;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class UsersRepositoryImpl  {
    private static final String CREATE_USER = "INSERT INTO users (username, passw, email) VALUES (?, ?, ?)";
    private static final String GET_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(UsersRepositoryImpl.class);
    private final SqlSessionFactory sqlSessionFactory;


    public UsersRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Create a new user
    public void createUser(Users user) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_USER,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            logger.info("User created successfully: " + user.getUsername());
        } catch (SQLException e) {
            logger.error("Error creating user: " + user.getUsername(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a user by username
    public Users getUserByUsername(String username) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME)
        ) {
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
        return null; // Return null if no user found
    }
}
