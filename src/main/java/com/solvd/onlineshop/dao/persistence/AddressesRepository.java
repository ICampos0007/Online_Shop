package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.bin.Users;
import org.apache.ibatis.annotations.Param;

public interface AddressesRepository {

    void create(Addresses addresses, int usersId);


    void deleteById(int id);

}
