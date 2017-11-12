package com.bulletproof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bulletproof.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
