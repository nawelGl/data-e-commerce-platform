package com.episen.order.application.service;

import com.episen.order.application.dto.*;
import com.episen.order.domain.entity.Order;
import com.episen.order.domain.enums.OrderStatus;
import com.episen.order.domain.repository.OrderRepository;
import com.episen.order.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation du service de gestion des commandes.
 * Ici nous mettons seulement les méthodes vides pour commencer.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        // TODO: implémenter la création d’une commande
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        // TODO: récupérer une commande par ID
        return null;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        // TODO: retourner la liste de toutes les commandes
        return null;
    }

    @Override
    public List<OrderResponseDto> getOrdersByUser(Long userId) {
        // TODO: filtrer par userId
        return null;
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(String status) {
        // TODO: filtrer par statut
        return null;
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, UpdateOrderStatusRequestDto request) {
        // TODO: mettre à jour le statut
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        // TODO: annuler une commande (DELETE)
    }
}