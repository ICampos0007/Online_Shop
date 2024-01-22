package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Promotions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Date;

public class PromotionsRepositoryImpl {
    private static final String CREATE_SEASONAL_PROMOTION = "INSERT INTO seasonal_promotions (promotion_name, start_date, end_date, product_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM seasonal_promotions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE seasonal_promotions SET promotion_name = ?, start_date = ?, end_date = ?, product_id = ? " +
            "WHERE id = ?";
    private static final String GET_BY_PROMOTION_NAME = "SELECT * FROM seasonal_promotions WHERE promotion_name = ?";
    private static final String GET_BY_PRODUCT_ID = "SELECT * FROM seasonal_promotions WHERE product_id = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(PromotionsRepositoryImpl.class);


    // Insert a new promotion
    public void addPromotion(Promotions promotion) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_SEASONAL_PROMOTION,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, promotion.getPromotion_Name());
            preparedStatement.setTimestamp(2, promotion.getStart_Date());
            preparedStatement.setTimestamp(3, promotion.getEnd_Date());
            preparedStatement.setInt(4, promotion.getProduct_Id());
            preparedStatement.executeUpdate();
            logger.info("Promotion created successfully: " + promotion.getId());
        } catch (SQLException e) {
            logger.error("Error creating promotion: " + promotion.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a promotion by ID
    public Promotions getPromotionById(int promotionId) {
        Connection connection = ConnectionPool.getConnection();
        Promotions promotion = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving promotion: " + promotionId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return promotion;
    }

    // Update promotion information
    public void updatePromotion(Promotions promotion) {
        Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setString(1, promotion.getPromotion_Name());
            preparedStatement.setTimestamp(2, promotion.getStart_Date());
            preparedStatement.setTimestamp(3, promotion.getEnd_Date());
            preparedStatement.setInt(4, promotion.getProduct_Id());
            preparedStatement.setInt(5, promotion.getId());
            preparedStatement.executeUpdate();
            logger.info("Promotion updated successfully: " + promotion.getId());
        } catch (SQLException e) {
            logger.error("Error updating promotion: " + promotion.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public Promotions getPromotionByName(String promotionName) {
        Connection connection = ConnectionPool.getConnection();
        Promotions promotion = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PROMOTION_NAME)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving promotion by name: " + promotionName, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return promotion;
    }

    public Promotions getPromotionByProductId(int productId) {
        Connection connection = ConnectionPool.getConnection();
        Promotions promotion = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PRODUCT_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving promotion by product ID: " + productId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return promotion;
    }
}
