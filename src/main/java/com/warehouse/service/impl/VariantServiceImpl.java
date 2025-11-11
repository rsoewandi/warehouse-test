package com.warehouse.service.impl;

import com.warehouse.entity.Item;
import com.warehouse.entity.Variant;
import com.warehouse.repo.ItemRepository;
import com.warehouse.repo.VariantRepository;
import com.warehouse.service.VariantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;
    private final ItemRepository itemRepository;

    public VariantServiceImpl(VariantRepository variantRepository, ItemRepository itemRepository) {
        this.variantRepository = variantRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Variant> findAll(Pageable pageable) {
        return variantRepository.findAll(pageable);
    }

    @Override
    public Variant findById(Long id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant not found"));
    }

    @Override
    public Variant create(Long itemId, Variant variant) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        variant.setItem(item);
        return variantRepository.save(variant);
    }

    @Override
    public Variant update(Long id, Variant variant) {
        Variant existing = findById(id);
        existing.setColor(variant.getColor());
        existing.setSize(variant.getSize());
        existing.setCode(variant.getCode());
        return variantRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        variantRepository.deleteById(id);
    }
}
