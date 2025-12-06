package com.episen.ms_product.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.episen.ms_product.domain.entity.Product;

/**
 * Repository pour l'entité Product.
 * Best practices :
 * - Utilisation de Spring Data JPA pour réduire le code boilerplate
 * - Méthodes de requête dérivées pour une meilleure lisibilité
 * - Queries personnalisées avec @Query si nécessaire
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Recherche un produit par id (méthode de requête dérivée)
     */
    Optional<Product> findById(Long id);

    /**
     * Recherche un produit par nom (méthode de requête dérivée)
     */
    Optional<Product> findByName(String name);
}
