package com.example.shop.UseCases;

import com.example.shop.DTOs.ItemDTO;
import com.example.shop.DomainModels.Item;
import com.example.shop.DomainModels.PurchaseData;

public interface ItemUseCase {
    Item addItem(ItemDTO itemDTO);
    void deleteItem(Long id);
    Item updateItem(Long id, ItemDTO itemDTO);
    PurchaseData buyItem(Long id, int quantity);

}