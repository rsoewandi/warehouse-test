package com.warehouse.controller;

import com.warehouse.entity.Variant;
import com.warehouse.response.ApiResponse;
import com.warehouse.service.VariantService;
import com.warehouse.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @Operation(summary = "Get all variants with pagination")
    @GetMapping
    public ApiResponse<Page<Variant>> getAllVariants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseUtil.success(variantService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<Variant> getVariantById(@PathVariable Long id) {
        return ResponseUtil.success(variantService.findById(id));
    }

    @PostMapping
    public ApiResponse<Variant> createVariant(@PathVariable Long itemId, @RequestBody Variant variant) {
        return ResponseUtil.success(variantService.create(itemId,variant));
    }

    @PutMapping("/{id}")
    public ApiResponse<Variant> updateVariant(@PathVariable Long id, @RequestBody Variant variant) {
        return ResponseUtil.success(variantService.update(id, variant));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteVariant(@PathVariable Long id) {
        variantService.delete(id);
        return ResponseUtil.success("Variant deleted successfully", null);
    }
}
