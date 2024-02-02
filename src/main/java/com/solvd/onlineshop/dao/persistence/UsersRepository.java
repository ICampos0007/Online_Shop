package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UsersRepository {
    void create(Users users);

    Optional<Users> findById(int id);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);


    void updateById(@Param("users") Users users, @Param("usersId") int id);

    void deleteById(int id);
}

