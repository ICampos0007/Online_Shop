package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Carts;
import com.solvd.onlineshop.dao.CartsRepositoryImpl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartsService {

    private static final Logger logger = LogManager.getLogger(CartsService.class);
    private CartsRepositoryImpl cartsRepository;

    public CartsService() {
        this.cartsRepository = new CartsRepositoryImpl();
    }

    // Create a new cart and check if the user already has one
    public void createCart(int id, int userId) throws SQLException {
        // Check if the user already has a cart
        Carts existingCart = cartsRepository.getCartByUserId(userId);

        if (existingCart == null) {
            // User does not have a cart, create a new one
            Carts newCart = new Carts(id, userId);
            newCart.setUser_Id(userId);

            // Create the new cart
            cartsRepository.addCart(newCart);
            logger.info("Cart created successfully for user ID: " + userId);
        } else {
            // User already has a cart
            logger.info("User with ID " + userId + " already has a cart (ID: " + existingCart.getId() + ")");
        }

    }

    public boolean isCartExists(int userId) throws SQLException {
        Carts existingCart = cartsRepository.getCartByUserId(userId);
        return existingCart != null;
    }
}
