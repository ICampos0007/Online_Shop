package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Orders;
import java.util.Optional;

public interface OrderService {
    void create(Orders orders);
    Optional<Orders> findById(int id);



    void deleteById(int id);
}
