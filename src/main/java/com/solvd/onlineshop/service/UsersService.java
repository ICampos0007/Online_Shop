package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UsersService{

    void create(Users users);


    Optional<Users> getById(int id);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);


    void updateById(@Param("users") Users users, @Param("usersId") int id);


    void delete(int id);
}
