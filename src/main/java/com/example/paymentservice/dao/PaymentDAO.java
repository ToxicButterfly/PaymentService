package com.example.paymentservice.dao;

import com.example.paymentservice.model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends JpaRepository<PaymentTransaction, Integer> {
    Optional<List<PaymentTransaction>> findAllByDriverId(Integer id);
    Optional<List<PaymentTransaction>> findAllByPassengerId(Integer id);

}
