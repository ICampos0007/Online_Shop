package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Reviews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReviewsRepositoryImpl {
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(ReviewsRepositoryImpl.class);


    // Insert a new review
    public void addReview(Reviews review) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "INSERT INTO reviews (id, user_id, product_id, rating, comment, review_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, review.getId());
                preparedStatement.setInt(2, review.getUser_Id());
                preparedStatement.setInt(3, review.getProduct_Id());
                preparedStatement.setInt(4, review.getRating());
                preparedStatement.setString(5, review.getComment());
                preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(review.getReview_Date()));
                preparedStatement.executeUpdate();
                logger.info("Review created successfully: " + review.getId());
            }
        } catch (SQLException e) {
            logger.error("Error creating review: " + review.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve a review by ID
    public Reviews getReviewById(int reviewId) {
        Reviews review = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM reviews WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, reviewId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        review = new Reviews(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("rating"),
                                resultSet.getString("comment"),
                                resultSet.getDate("review_date")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving review: " + reviewId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return review;
    }

    // Update review information
    public void updateReview(Reviews review) {
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "UPDATE reviews SET user_id = ?, product_id = ?, rating = ?, " +
                    "comment = ?, review_date = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, review.getUser_Id());
                preparedStatement.setInt(2, review.getProduct_Id());
                preparedStatement.setInt(3, review.getRating());
                preparedStatement.setString(4, review.getComment());
                preparedStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(review.getReview_Date()));
                preparedStatement.setInt(6, review.getId());
                preparedStatement.executeUpdate();
                logger.info("Review updated successfully: " + review.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating review: " + review.getId(), e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    // Retrieve a review by User ID
    public Reviews getReviewByUserIdAndProductId(int userId, int productId) {
        Reviews review = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            String query = "SELECT * FROM reviews WHERE user_id = ? AND product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        review = new Reviews(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("rating"),
                                resultSet.getString("comment"),
                                resultSet.getDate("review_date")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving review by user ID and product ID: " + userId + ", " + productId, e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return review;
    }
}
