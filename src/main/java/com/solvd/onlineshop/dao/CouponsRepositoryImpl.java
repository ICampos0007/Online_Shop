package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Coupons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;

public class CouponsRepositoryImpl {
    private static final String CREATE_COUPON = "INSERT INTO coupons (codes, discount, expiration_date, user_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM coupons WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE coupons SET codes = ?, discount_percentage = ?, " +
            "expiration_date = ?, user_id = ? WHERE id = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM coupons WHERE user_id = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(CouponsRepositoryImpl.class);

    // Insert a new coupon
    public void addCoupon(Coupons coupon) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_COUPON,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, coupon.getId());
            preparedStatement.setString(2, coupon.getCodes());
            preparedStatement.setDouble(3, coupon.getDiscount());
            preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(coupon.getExpiration_Date()));
            preparedStatement.setInt(5, coupon.getUser_Id());
            preparedStatement.executeUpdate();
            logger.info("Coupon created successfully: " + coupon.getId());
        } catch (SQLException e) {
            logger.error("Error creating coupon: " + coupon.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a coupon by ID
    public Coupons getCouponById(int couponId) {
        Connection connection = ConnectionPool.getConnection();
        Coupons coupon = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving coupon: " + couponId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return coupon;
    }

    // Update coupon information
    public void updateCoupon(Coupons coupon) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setString(1, coupon.getCodes());
            preparedStatement.setDouble(2, coupon.getDiscount());
            preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(coupon.getExpiration_Date()));
            preparedStatement.setInt(4, coupon.getUser_Id());
            preparedStatement.setInt(5, coupon.getId());
            preparedStatement.executeUpdate();
            logger.info("Coupon updated successfully: " + coupon.getId());
        } catch (SQLException e) {
            logger.error("Error updating coupon: " + coupon.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public Coupons getCouponByUserId(int userId) {
        Connection connection = ConnectionPool.getConnection();
        Coupons coupon = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving coupon by user ID: " + userId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return coupon;
    }
}

