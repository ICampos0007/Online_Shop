package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UsersRepositoryMyBatisImpl implements UsersRepository {

    private static final Logger logger = LogManager.getLogger(UsersRepositoryMyBatisImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    public UsersRepositoryMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void create(Users users) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            usersRepository.create(users);
        }
    }

    @Override
    public Optional<Users> findById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            return usersRepository.findById(id);
        }
    }
//
//    @Override
//    public Optional<Users> findByUsername(int id) {
//        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
//            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
//            return usersRepository.findByUsername(id);
//        }
//    }

    @Override
    public void updateById(Users users, int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
             usersRepository.updateById(users, id);
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            usersRepository.deleteById(id);
        }
    }
}
