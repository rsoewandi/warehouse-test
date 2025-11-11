package com.warehouse.service;

import com.warehouse.entity.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VariantService {
    Page<Variant> findAll(Pageable pageable);
    Variant findById(Long id);
    Variant create(Long itemId, Variant variant);
    Variant update(Long id, Variant variant);
    void delete(Long id);
}
