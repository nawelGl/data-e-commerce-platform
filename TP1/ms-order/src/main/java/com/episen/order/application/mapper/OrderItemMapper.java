package com.episen.order.application.mapper;

import org.springframework.stereotype.Component;

import com.episen.order.application.dto.OrderItemResponseDto;
import com.episen.order.domain.entity.OrderItem;

/**
 * Mapper pour convertir entre OrderItem et son DTO.
 */
@Component
public class OrderItemMapper {

    /**
     * Convertit une entité OrderItem en DTO de réponse.
     */
    public OrderItemResponseDto toDto(OrderItem item) {
        return OrderItemResponseDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}