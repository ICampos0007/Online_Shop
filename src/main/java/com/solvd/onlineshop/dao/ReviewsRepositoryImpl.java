package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Reviews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;

public class ReviewsRepositoryImpl {
    private static final String CREATE_REVIEW = "INSERT INTO reviews (user_id, product_id, rating, comment, review_date) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM reviews WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE reviews SET user_id = ?, product_id = ?, rating = ?, " +
            "comment = ?, review_date = ? WHERE id = ?";
    private static final String GET_BY_USER_ID_AND_PRODUCT_ID = "SELECT * FROM reviews WHERE user_id = ? AND product_id = ?";
    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(ReviewsRepositoryImpl.class);


    // Insert a new review
    public void addReview(Reviews review) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_REVIEW,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, review.getUser_Id());
            preparedStatement.setInt(2, review.getProduct_Id());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getComment());
            preparedStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(review.getReview_Date()));
            preparedStatement.executeUpdate();
            logger.info("Review created successfully: " + review.getId());
        } catch (SQLException e) {
            logger.error("Error creating review: " + review.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a review by ID
    public Reviews getReviewById(int reviewId) {
        Connection connection = ConnectionPool.getConnection();
        Reviews review = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving review: " + reviewId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return review;
    }

    // Update review information
    public void updateReview(Reviews review) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setInt(1, review.getUser_Id());
            preparedStatement.setInt(2, review.getProduct_Id());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getComment());
            preparedStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(review.getReview_Date()));
            preparedStatement.setInt(6, review.getId());
            preparedStatement.executeUpdate();
            logger.info("Review updated successfully: " + review.getId());
        } catch (SQLException e) {
            logger.error("Error updating review: " + review.getId(), e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }
    // Retrieve a review by User ID
    public Reviews getReviewByUserIdAndProductId ( int userId, int productId){
        Connection connection = ConnectionPool.getConnection();
        Reviews review = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID_AND_PRODUCT_ID)
        ) {
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
        } catch (SQLException e) {
            logger.error("Error retrieving review by user ID and product ID: " + userId + ", " + productId, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return review;
    }
}
