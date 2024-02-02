package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.bin.Users;
import java.util.Optional;

public interface AddressesService{

    int create(Addresses addresses, Users usersId);

    Optional<Addresses> getById(int id);

    void  update(Addresses addresses);

    void  delete(int id);
}
