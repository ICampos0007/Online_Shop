package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.Orders;

public interface OrdersRepository {
    void create(Orders orders);

    void deleteById(int id);
}
