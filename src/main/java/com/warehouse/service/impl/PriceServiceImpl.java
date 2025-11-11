package com.warehouse.service.impl;

import com.warehouse.entity.Price;
import com.warehouse.entity.Variant;
import com.warehouse.repo.PriceRepository;
import com.warehouse.repo.VariantRepository;
import com.warehouse.service.PriceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final VariantRepository variantRepository;

    public PriceServiceImpl(PriceRepository priceRepository, VariantRepository variantRepository) {
        this.priceRepository = priceRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public List<Price> findByVariant(Long variantId) {
        return priceRepository.findAll().stream()
                .filter(p -> p.getVariant().getId().equals(variantId))
                .toList();
    }

    @Override
    public Price create(Long variantId, Price price) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new EntityNotFoundException("Variant not found"));
        price.setVariant(variant);
        return priceRepository.save(price);
    }

    @Override
    public Double getActivePrice(Long variantId) {
        LocalDate today = LocalDate.now();
        return priceRepository.findActivePrice(variantId, today)
                .map(Price::getPrice)
                .orElseThrow(() -> new RuntimeException("No active price"));
    }
}
