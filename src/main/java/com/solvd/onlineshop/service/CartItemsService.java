package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.CartItems;
import com.solvd.onlineshop.dao.CartItemsRepositoryImpl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartItemsService {

    private static final Logger logger = LogManager.getLogger(CartItemsService.class);
    private CartItemsRepositoryImpl cartItemsRepository;

    public CartItemsService() {
        this.cartItemsRepository = new CartItemsRepositoryImpl();
    }

    // Create a new CartItem and check if the cart_id already exists
    public void createCartItem(int id, int cartId, int productId, int quantity) throws SQLException {
        // Check if the cart_id already exists
        CartItems existingCartItem = cartItemsRepository.getCartItemByCartId(cartId);

        if (existingCartItem == null) {
            // cart_id does not exist, create a new CartItem
            CartItems newCartItem = new CartItems(id, cartId, productId, quantity);

            // Create the new CartItem
            cartItemsRepository.addCartItem(newCartItem);
            logger.info("CartItem created successfully for cart ID: " + cartId);
        } else {
            // cart_id already exists
            logger.info("Cart with ID " + cartId + " already has a CartItem (ID: " + existingCartItem.getId() + ")");
        }
    }

    public boolean isCartItemsExists(int cart_id) throws SQLException {
        CartItems existingCartItems = cartItemsRepository.getCartItemByCartId(cart_id);
        return  existingCartItems != null;
    }
}
