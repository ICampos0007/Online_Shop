package com.solvd.onlineshop.dao.persistence;

import com.solvd.onlineshop.bin.PaymentMethods;

import java.util.Optional;

public interface PaymentMethodsRepository {
    void create(PaymentMethods paymentMethods);

    Optional<PaymentMethods> findById(int id);
    void deleteById(int id);
}
