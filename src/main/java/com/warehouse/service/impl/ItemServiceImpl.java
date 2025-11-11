package com.warehouse.service.impl;

import com.warehouse.entity.Item;
import com.warehouse.repo.ItemRepository;
import com.warehouse.request.RequestItem;
import com.warehouse.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public Item createItem(RequestItem req) {
        validateRequest(req);
        Item item = Item.reqItem(req);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long id, RequestItem req) {
        validateRequest(req);
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));

        existing.setName(req.getName());
        existing.setSku(req.getSku());
        existing.setUpdatedBy(req.getUserName());
        existing.setDescription(req.getDescription());
        existing.setUpdatedDatetime(LocalDateTime.now());

        return itemRepository.save(existing);
    }

    @Override
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    private void validateRequest(RequestItem req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new RuntimeException("Item name is required");
        }

        if (req.getSku() == null || req.getSku().isBlank()) {
            throw new RuntimeException("SKU is required");
        }

        if (req.getUserName() == null || req.getUserName().isBlank()) {
            throw new RuntimeException("Username is required");
        }
    }
}
