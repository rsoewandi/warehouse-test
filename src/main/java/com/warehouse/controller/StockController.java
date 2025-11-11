package com.warehouse.controller;


import com.warehouse.response.ApiResponse;
import com.warehouse.service.StockService;
import com.warehouse.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "Add stock quantity for a variant")
    @PostMapping("/add")
    public ApiResponse<Void> addStock(
            @RequestParam Long variantId,
            @RequestParam int qty,
            @RequestParam(defaultValue = "Manual Adjustment") String reference) {
        stockService.addStock(variantId, qty, reference);
        return ResponseUtil.success("Stock added successfully", null);
    }

    @Operation(summary = "Reduce stock quantity for a variant")
    @PostMapping("/reduce")
    public ApiResponse<Void> reduceStock(
            @RequestParam Long variantId,
            @RequestParam int qty,
            @RequestParam(defaultValue = "Manual Reduction") String reference) {

        stockService.reduceStock(variantId, qty, reference);
        return ResponseUtil.success("Stock reduced successfully", null);
    }

    @Operation(summary = "Get available stock for a variant")
    @GetMapping("/available/{variantId}")
    public ApiResponse<Integer> getAvailableStock(@PathVariable Long variantId) {
        return ResponseUtil.success(stockService.getAvailableStock(variantId));
    }
}
