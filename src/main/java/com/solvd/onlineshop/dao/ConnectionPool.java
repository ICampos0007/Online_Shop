package com.solvd.onlineshop.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final String CONFIG_FILE = "config.properties";
    private static final int POOL_SIZE = 20;
    private static final List<Connection> connectionPool = new ArrayList<>(POOL_SIZE);

    static {
        ConnectionPool();
    }
    private static void ConnectionPool() {
        Properties properties = loadProperties();

        String jdbcUrl = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            Class.forName(properties.getProperty("driver"));

            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the connection pool");
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading config.properties");
        }
        return properties;
    }
    public static Connection getConnection() {
        if (connectionPool.isEmpty()) {
            try {
                throw new SQLException("Connection pool exhausted");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }
    public static void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public static void closeAllConnections() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionPool.clear();
    }
}
