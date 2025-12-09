package com.episen.order.domain.repository;


import com.episen.order.domain.entity.Order;
import com.episen.order.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Récupère toutes les commandes d'un utilisateur.
     */
    List<Order> findByUserId(Long userId);

    /**
     * Récupère toutes les commandes avec un statut donné.
     */
    List<Order> findByStatus(OrderStatus status);

    /**
     * (Optionnel) Récupère les commandes d'un utilisateur avec un statut donné.
     */
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);
}