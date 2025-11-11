package com.warehouse.repo;

import com.warehouse.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT COALESCE(SUM(s.quantityChange), 0) FROM Stock s WHERE s.variant.id = :variantId")
    int sumStockByVariantId(@Param("variantId") Long variantId);
}