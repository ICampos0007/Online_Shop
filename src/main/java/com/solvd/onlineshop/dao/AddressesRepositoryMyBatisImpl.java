package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.dao.persistence.AddressesRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressesRepositoryMyBatisImpl implements AddressesRepository {
    private static final Logger logger = LogManager.getLogger(AddressesRepositoryMyBatisImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    public AddressesRepositoryMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void create(Addresses addresses, int usersId) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            AddressesRepository addressesRepository = sqlSession.getMapper(AddressesRepository.class);
            addressesRepository.create(addresses, usersId);
            logger.info("Order created successfully: " + addresses.getId());
        }
    }


    @Override
    public void deleteById(int id) {
        try(SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            AddressesRepository addressesRepository = sqlSession.getMapper(AddressesRepository.class);
            addressesRepository.deleteById(id);
            logger.info("Address id has been deleted" + id);
        }
    }
}
