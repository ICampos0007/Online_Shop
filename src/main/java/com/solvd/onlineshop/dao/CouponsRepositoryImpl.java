package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Coupons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CouponsRepositoryImpl {
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(CouponsRepositoryImpl.class);

    // Insert a new coupon
    public void addCoupon(Coupons coupon) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "INSERT INTO coupons (id, codes, discount, expiration_date, user_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, coupon.getId());
                preparedStatement.setString(2, coupon.getCodes());
                preparedStatement.setDouble(3, coupon.getDiscount());
                preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(coupon.getExpiration_Date()));
                preparedStatement.setInt(5, coupon.getUser_Id());
                preparedStatement.executeUpdate();
                logger.info("Coupon created successfully: " + coupon.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating coupon: " + coupon.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve a coupon by ID
    public Coupons getCouponById(int couponId) {
        Coupons coupon = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM coupons WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, couponId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        coupon = new Coupons(
                                resultSet.getInt("id"),
                                resultSet.getString("coupon_code"),
                                resultSet.getDouble("discount"),
                                resultSet.getDate("expiration_date"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving coupon: " + couponId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return coupon;
    }

    // Update coupon information
    public void updateCoupon(Coupons coupon) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "UPDATE coupons SET codes = ?, discount_percentage = ?, " +
                    "expiration_date = ?, user_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, coupon.getCodes());
                preparedStatement.setDouble(2, coupon.getDiscount());
                preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(coupon.getExpiration_Date()));
                preparedStatement.setInt(4, coupon.getUser_Id());
                preparedStatement.setInt(5, coupon.getId());
                preparedStatement.executeUpdate();
                logger.info("Coupon updated successfully: " + coupon.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating coupon: " + coupon.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    public Coupons getCouponByUserId(int userId) {
        Coupons coupon = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM coupons WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        coupon = new Coupons(
                                resultSet.getInt("id"),
                                resultSet.getString("codes"),
                                resultSet.getDouble("discount"),
                                resultSet.getDate("expiration_date"),
                                resultSet.getInt("user_id")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving coupon by user ID: " + userId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return coupon;
    }
}

