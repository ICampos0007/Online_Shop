package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.ShippingMethods;
import com.solvd.onlineshop.dao.mybatisimpl.ShippingMethodsRepositoryImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShippingMethodsService {
    private static final Logger logger = LogManager.getLogger(ShippingMethodsService.class);

    private ShippingMethodsRepositoryImpl shippingMethodsRepository;

    public ShippingMethodsService() {
        this.shippingMethodsRepository = new ShippingMethodsRepositoryImpl();
    }

    // Create a new shipping method
    public void createShippingMethod(int id, String shippingMethodName, double shippingCost, int orderId) {
        // Check if the order_id already exists
        if (shippingMethodsRepository.getShippingMethodByOrderId(orderId) != null) {
            // Log an error and return
            logger.error("Error creating shipping method. Order with ID '" + orderId + "' already has a shipping method.");
            return;
        }

        ShippingMethods newShippingMethod = new ShippingMethods(id, shippingMethodName, shippingCost, orderId);

        // Add the new shipping method to the repository
        shippingMethodsRepository.addShippingMethod(newShippingMethod);
        logger.info("Shipping method created successfully: " + newShippingMethod.getId());
    }
}
