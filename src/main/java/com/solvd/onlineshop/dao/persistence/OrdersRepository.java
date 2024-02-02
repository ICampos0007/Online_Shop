package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.Orders;

import java.util.Optional;

public interface OrdersRepository {
    void create(Orders orders);

    Optional<Orders> findById(int id);

    void deleteById(int id);
}
