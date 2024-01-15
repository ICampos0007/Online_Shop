package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Promotions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PromotionsRepositoryImpl {
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(PromotionsRepositoryImpl.class);


    // Insert a new promotion
    public void addPromotion(Promotions promotion) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "INSERT INTO seasonal_promotions (id, promotion_name, start_date, end_date, product_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, promotion.getId());
                preparedStatement.setString(2, promotion.getPromotion_Name());
                preparedStatement.setTimestamp(3, promotion.getStart_Date());
                preparedStatement.setTimestamp(4, promotion.getEnd_Date());
                preparedStatement.setInt(5, promotion.getProduct_Id());
                preparedStatement.executeUpdate();
                logger.info("Promotion created successfully: " + promotion.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating promotion: " + promotion.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve a promotion by ID
    public Promotions getPromotionById(int promotionId) {
        Promotions promotion = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM seasonal_promotions WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, promotionId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        promotion = new Promotions(
                                resultSet.getInt("id"),
                                resultSet.getString("promotion_name"),
                                resultSet.getTimestamp("start_date"),
                                resultSet.getTimestamp("end_date"),
                                resultSet.getInt("product_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving promotion: " + promotionId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return promotion;
    }

    // Update promotion information
    public void updatePromotion(Promotions promotion) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "UPDATE seasonal_promotions SET promotion_name = ?, start_date = ?, end_date = ?, product_id = ? " +
                    "WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, promotion.getPromotion_Name());
                preparedStatement.setTimestamp(2, promotion.getStart_Date());
                preparedStatement.setTimestamp(3, promotion.getEnd_Date());
                preparedStatement.setInt(4, promotion.getProduct_Id());
                preparedStatement.setInt(5, promotion.getId());
                preparedStatement.executeUpdate();
                logger.info("Promotion updated successfully: " + promotion.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating promotion: " + promotion.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    public Promotions getPromotionByName(String promotionName) {
        Promotions promotion = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM seasonal_promotions WHERE promotion_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, promotionName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        promotion = new Promotions(
                                resultSet.getInt("id"),
                                resultSet.getString("promotion_name"),
                                resultSet.getTimestamp("start_date"),
                                resultSet.getTimestamp("end_date"),
                                resultSet.getInt("product_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving promotion by name: " + promotionName, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return promotion;
    }

    public Promotions getPromotionByProductId(int productId) {
        Promotions promotion = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM seasonal_promotions WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        promotion = new Promotions(
                                resultSet.getInt("id"),
                                resultSet.getString("promotion_name"),
                                resultSet.getTimestamp("start_date"),
                                resultSet.getTimestamp("end_date"),
                                resultSet.getInt("product_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving promotion by product ID: " + productId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return promotion;
    }
}
