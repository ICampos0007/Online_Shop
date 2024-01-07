package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.dao.persistence.AddressesRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressesRepositoryImpl {

    private static final Logger logger = LogManager.getLogger(AddressesRepositoryImpl.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final SqlSessionFactory sqlSessionFactory;

    public AddressesRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Insert a new address
    public void addAddress(Addresses address) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO addresses (id, address_line_1, address_line_2, city, state, postal_code, user_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, address.getId());
                preparedStatement.setString(2, address.getAddress_line_1());
                preparedStatement.setString(3, address.getAddress_line_2());
                preparedStatement.setString(4, address.getCity());
                preparedStatement.setString(5, address.getState());
                preparedStatement.setInt(6, address.getPostal_code());
                preparedStatement.setInt(7, address.getUser_id());
                preparedStatement.executeUpdate();
                logger.info("Address created successfully: " + address.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating address: " + address.getId(), e);
        }
    }

    // Retrieve an address by ID
    public Addresses getAddressById(int addressId) {
        Addresses address = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM addresses WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, addressId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        address = new Addresses(
                                resultSet.getInt("id"),
                                resultSet.getString("address_line_1"),
                                resultSet.getString("address_line_2"),
                                resultSet.getString("city"),
                                resultSet.getString("state"),
                                resultSet.getInt("postal_code"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving address: " + addressId, e);
        }
        return address;
    }

    // Update address information
    public void updateAddress(Addresses address) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "UPDATE addresses SET address_line_1 = ?, address_line_2 = ?, city = ?, state = ?, " +
                    "postal_code = ?, user_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, address.getAddress_line_1());
                preparedStatement.setString(2, address.getAddress_line_2());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getState());
                preparedStatement.setInt(5, address.getPostal_code());
                preparedStatement.setInt(6, address.getUser_id());
                preparedStatement.setInt(7, address.getId());
                preparedStatement.executeUpdate();
                logger.info("Address updated successfully: " + address.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating address: " + address.getId(), e);
        }
    }

    // Retrieve an address by user ID
    public Addresses getAddressByUserId(int addressId) {
        Addresses address = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM addresses WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, addressId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        address = new Addresses(
                                resultSet.getInt("id"),
                                resultSet.getString("address_line_1"),
                                resultSet.getString("address_line_2"),
                                resultSet.getString("city"),
                                resultSet.getString("state"),
                                resultSet.getInt("postal_code"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving address: " + addressId, e);
        }
        return address;
    }
}
