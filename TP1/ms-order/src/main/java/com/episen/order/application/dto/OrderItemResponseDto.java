
package com.episen.order.application.dto;
import lombok.*;

import java.math.BigDecimal;

/**
 * DTO représentant une ligne de commande dans les réponses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {

    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
