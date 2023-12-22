package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Reviews;
import com.solvd.onlineshop.dao.ReviewsRepositoryImpl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewsService {
    private static final Logger logger = LogManager.getLogger(ReviewsService.class);

    private ReviewsRepositoryImpl reviewsRepository;

    public ReviewsService() {
        this.reviewsRepository = new ReviewsRepositoryImpl();
    }

    // Create a new review
    public void createReview(int id, int userId, int productId, int rating, String comment, Date reviewDate) {
        // Check if the user has already left a review for the product
        if (reviewsRepository.getReviewByUserIdAndProductId(userId, productId) != null) {
            // Log an error and return
            logger.error("Error creating review. User with ID '" + userId + "' already left a review for product ID '" + productId + "'.");
            return;
        }

        Reviews newReview = new Reviews(id, userId, productId, rating, comment, reviewDate);

        // Add the new review to the repository
        reviewsRepository.addReview(newReview);
        logger.info("Review created successfully: " + newReview.getId());
    }
}
