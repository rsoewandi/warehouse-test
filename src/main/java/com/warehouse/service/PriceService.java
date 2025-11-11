package com.warehouse.service;

import com.warehouse.entity.Price;

import java.util.List;

public interface PriceService {
    List<Price> findByVariant(Long variantId);
    Price create(Long variantId, Price price);
    Double getActivePrice(Long variantId);
}
