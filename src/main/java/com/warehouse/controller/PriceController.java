package com.warehouse.controller;

import com.warehouse.entity.Price;
import com.warehouse.response.ApiResponse;
import com.warehouse.service.PriceService;
import com.warehouse.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Get all prices by variant ID")
    @GetMapping("/variant/{variantId}")
    public ApiResponse<List<Price>> getPricesByVariant(@PathVariable Long variantId) {
        return ResponseUtil.success(priceService.findByVariant(variantId));
    }

    @Operation(summary = "Create a new price for a variant")
    @PostMapping("/variant/{variantId}")
    public ApiResponse<Price> createPrice(@PathVariable Long variantId, @RequestBody Price price) {
        return ResponseUtil.success(priceService.create(variantId, price));
    }

    @Operation(summary = "Get active price of a variant")
    @GetMapping("/variant/{variantId}/active")
    public Double getActivePrice(@PathVariable Long variantId) {
        return priceService.getActivePrice(variantId);
    }
}