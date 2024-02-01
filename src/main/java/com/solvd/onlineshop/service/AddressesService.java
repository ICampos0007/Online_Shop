package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.bin.Users;

public interface AddressesService{

    int create(Addresses addresses, Users usersId);

    Addresses getById(int id);

    void  update(Addresses addresses);

    void  delete(int id);
}
