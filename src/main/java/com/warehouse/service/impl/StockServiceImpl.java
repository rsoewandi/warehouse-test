package com.warehouse.service.impl;

import com.warehouse.entity.Stock;
import com.warehouse.entity.Variant;
import com.warehouse.repo.StockRepository;
import com.warehouse.repo.VariantRepository;
import com.warehouse.service.StockService;
import com.warehouse.util.StockType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final VariantRepository variantRepository;

    public StockServiceImpl(StockRepository stockRepository, VariantRepository variantRepository) {
        this.stockRepository = stockRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    @Transactional
    public void addStock(Long variantId, int qty, String reference) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        Stock stock = new Stock();
        stock.setVariant(variant);
        stock.setQuantityChange(qty);
        stock.setType(StockType.INBOUND.name());
                stock.setReference(reference);
        stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void reduceStock(Long variantId, int qty, String reference) {
        int available = stockRepository.sumStockByVariantId(variantId);
        if (available < qty)
            throw new RuntimeException("Insufficient stock");

        Variant variant = variantRepository.getReferenceById(variantId);
        Stock stock = new Stock();
        stock.setVariant(variant);
        stock.setQuantityChange(-qty);
        stock.setType(StockType.SALE.name());
                stock.setReference(reference);
        stockRepository.save(stock);
    }

    @Override
    public int getAvailableStock(Long variantId) {
        return stockRepository.sumStockByVariantId(variantId);
    }
}