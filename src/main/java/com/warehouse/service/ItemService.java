package com.warehouse.service;

import com.warehouse.entity.Item;
import com.warehouse.request.RequestItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    Page<Item> getAllItems(Pageable pageable);
    Item getItemById(Long id);
    Item createItem(RequestItem item);
    Item updateItem(Long id, RequestItem item);
    void deleteItem(Long id);
}
