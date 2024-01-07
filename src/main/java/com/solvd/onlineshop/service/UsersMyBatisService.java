package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.DaoConfig;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

public class UsersMyBatisService {
    private static final Logger logger = LogManager.getLogger(UsersMyBatisService.class);
    private final UsersRepository usersRepository;

    public UsersMyBatisService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void createUser(Users users) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            // Use the existing UsersRepository instance from the class field
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);

            // Check if the user with the same ID already exists
            Optional<Users> existingUser = usersRepository.findById(users.getId());

            if (existingUser.isPresent()) {
                // User with the same ID already exists, log an error
                logger.error("User with ID {} already exists. Cannot create duplicate user.", users.getId());
            } else {
                // User doesn't exist, proceed with creating it
                usersRepository.create(users);
                logger.info("User created successfully: " + users.getUsername());
            }
        }
    }


    public Optional<Users> findUserById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            Optional<Users> user = usersRepository.findById(id);
            if (!user.isPresent()) {
                logger.error("User with ID {} not found", id);
            }
            return user;
            }
        }
    public void updateUser(Users users, int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            UsersRepository usersRepository = sqlSession.getMapper(UsersRepository.class);
            usersRepository.updateById(users,id);
        }
    }

    public void deleteUserById(int id) {
        try (SqlSession sqlSession = DaoConfig.getSessionFactory().openSession(true)) {
            // Use the existing UsersRepository instance from the class field
            if (findUserById(id).isPresent()) {
                usersRepository.deleteById(id);
            } else {
                logger.error("Cannot delete user with ID {}. User not found", id);
            }
        }
    }
}
