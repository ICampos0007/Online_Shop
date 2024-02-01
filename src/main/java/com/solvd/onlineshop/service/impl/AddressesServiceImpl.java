package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.AddressesRepositoryImpl;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.AddressesRepository;
import com.solvd.onlineshop.service.AddressesService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressesServiceImpl implements AddressesService {
    private static final Logger logger = LogManager.getLogger(AddressesServiceImpl.class);
    private final SqlSessionFactory sqlSessionFactory;
    private final AddressesRepositoryImpl addressesRepository;

    public AddressesServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.addressesRepository = new AddressesRepositoryImpl(sqlSessionFactory);
    }

    // Create a new address

//    public void createAddress(Addresses address, int usersId) {
//        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
//            AddressesRepository addressesRepository = sqlSession.getMapper(AddressesRepository.class);
//            addressesRepository.create(address, usersId);
//            logger.info("Order created successfully: " + address.getId());
//        }
//    }

    // Check if a user already has an address
    public boolean isUserHasAddress(int userId) throws SQLException {
        Addresses existingAddress = addressesRepository.getAddressByUserId(userId);
        return existingAddress != null;
    }

    // Example: Create a new address for a user if they don't have one
//    public void createAddressForUserIfNotExists(Addresses address) {
//        try {
//            if (!isUserHasAddress(address.getUser_id())) {
//                createAddress(address);
//            } else {
//                logger.info("User with ID " + address.getUser_id() + " already has an address.");
//            }
//        } catch (Exception e) {
//            logger.error("Error creating address for user: " + address.getUser_id(), e);
//        }
//    }


    @Override
    public int create(Addresses addresses, Users usersId) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            AddressesRepository addressesRepository = sqlSession.getMapper(AddressesRepository.class);
            addressesRepository.create(addresses, usersId.getId());
            logger.info("Order created successfully: " + addresses.getId());
        }
        return addresses.getId();
    }

    @Override
    public Addresses getById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            AddressesRepository addressesRepository = sqlSession.getMapper(AddressesRepository.class);
            return addressesRepository.getById(id);
        }

    }

    @Override
    public void update(Addresses addresses) {

    }

    @Override
    public void delete(int id) {

    }
}
