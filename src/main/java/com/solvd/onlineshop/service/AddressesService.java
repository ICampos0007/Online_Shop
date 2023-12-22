package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.dao.AddressesRepositoryImpl;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressesService {
    private static final Logger logger = LogManager.getLogger(AddressesService.class);
    private AddressesRepositoryImpl addressesRepository;

    public AddressesService() {
        this.addressesRepository = new AddressesRepositoryImpl();
    }

    // Create a new address
    public void createAddress(Addresses address) {
        addressesRepository.addAddress(address);
        logger.info("Address created successfully: " + address.getId());
    }

    // Check if a user already has an address
    public boolean isUserHasAddress(int userId) throws SQLException {
        Addresses existingAddress = addressesRepository.getAddressByUserId(userId);
        return existingAddress != null;
    }

    // Example: Create a new address for a user if they don't have one
    public void createAddressForUserIfNotExists(Addresses address) {
        try {
            if (!isUserHasAddress(address.getUser_id())) {
                createAddress(address);
            } else {
                logger.info("User with ID " + address.getUser_id() + " already has an address.");
            }
        } catch (Exception e) {
            logger.error("Error creating address for user: " + address.getUser_id(), e);
        }
    }
}
