package com.warehouse.repo;

import com.warehouse.entity.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    Page<Variant> findAll(Pageable pageable);
}