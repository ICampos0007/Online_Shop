package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Orders;
import com.solvd.onlineshop.bin.Users;

public interface OrderService {
    void create(Orders orders);

    void deleteById(int id);
}
