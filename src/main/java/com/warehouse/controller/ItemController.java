package com.warehouse.controller;

import com.warehouse.entity.Item;
import com.warehouse.request.RequestItem;
import com.warehouse.response.ApiResponse;
import com.warehouse.service.ItemService;
import com.warehouse.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ApiResponse<Page<Item>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseUtil.success(itemService.getAllItems(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<Item> getItemById(@PathVariable Long id) {
        return ResponseUtil.success(itemService.getItemById(id));
    }

    @PostMapping
    public ApiResponse<Item> createItem(@RequestBody RequestItem item) {
        return ResponseUtil.success(itemService.createItem(item));
    }

    @PutMapping("/{id}")
    public ApiResponse<Item> updateItem(@PathVariable Long id, @RequestBody RequestItem item) {
        return ResponseUtil.success(itemService.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseUtil.success("item deleted successfully",null);
    }
}
