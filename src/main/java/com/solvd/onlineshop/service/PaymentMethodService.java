package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.PaymentMethods;

import java.util.Optional;

public interface PaymentMethodService {
    void create(PaymentMethods paymentMethods);

    Optional<PaymentMethods> findById(int id);
    void deleteById(int id);
}
