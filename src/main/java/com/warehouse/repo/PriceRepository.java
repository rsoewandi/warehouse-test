package com.warehouse.repo;

import com.warehouse.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("""
    SELECT p FROM Price p
    WHERE p.variant.id = :variantId
      AND p.startDate <= :today
      AND (p.endDate IS NULL OR p.endDate > :today)
    ORDER BY p.startDate DESC
    """)
    Optional<Price> findActivePrice(@Param("variantId") Long variantId, @Param("today") LocalDate today);
}