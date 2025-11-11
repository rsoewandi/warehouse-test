package com.warehouse.service;

public interface StockService {
    void addStock(Long variantId, int qty, String reference);
    void reduceStock(Long variantId, int qty, String reference);
    int getAvailableStock(Long variantId);
}
