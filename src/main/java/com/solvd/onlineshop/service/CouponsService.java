package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Coupons;
import com.solvd.onlineshop.dao.CouponsRepositoryImpl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CouponsService {

    private static final Logger logger = LogManager.getLogger(CouponsService.class);
    private CouponsRepositoryImpl couponsRepository;

    public CouponsService() {
        this.couponsRepository = new CouponsRepositoryImpl();
    }

    // Create a new coupon and check if the user already has one
    public void createCoupon(int id, String couponCode, double discountPercentage, Date expirationDate, int userId) throws SQLException {
        // Check if the user already has a coupon
        Coupons existingCoupon = couponsRepository.getCouponByUserId(userId);

        if (existingCoupon == null) {
            // User does not have a coupon, create a new one
            Coupons newCoupon = new Coupons(id, couponCode, discountPercentage, expirationDate, userId);

            // Create the new coupon
            couponsRepository.addCoupon(newCoupon);
            logger.info("Coupon created successfully for user ID: " + userId);
        } else {
            // User already has a coupon
            logger.info("User with ID " + userId + " already has a coupon (ID: " + existingCoupon.getId() + ")");
        }
    }
}
