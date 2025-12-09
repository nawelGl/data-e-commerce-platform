package com.episen.order.infrastructure.web.controller;

import com.episen.order.application.dto.OrderRequestDto;
import com.episen.order.application.dto.OrderResponseDto;
import com.episen.order.application.dto.UpdateOrderStatusRequestDto;
import com.episen.order.application.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des commandes.
 *
 * Expose les endpoints :
 * - GET /api/v1/orders
 * - GET /api/v1/orders/{id}
 * - POST /api/v1/orders
 * - PUT /api/v1/orders/{id}/status
 * - DELETE /api/v1/orders/{id}
 * - GET /api/v1/orders/user/{userId}
 * - GET /api/v1/orders/status/{status}
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Créer une nouvelle commande.
     */
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto request) {

        OrderResponseDto response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les commandes.
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Récupérer une commande par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Récupérer les commandes d'un utilisateur.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderResponseDto> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Récupérer les commandes par statut.
     * Exemple : /api/v1/orders/status/PENDING
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable String status) {
        List<OrderResponseDto> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    /**
     * Mettre à jour le statut d'une commande.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequestDto request) {

        OrderResponseDto updated = orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Annuler / supprimer une commande.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}