package com.example.shop.Item;

public interface ItemUseCase {
    Item addItem(ItemDTO itemDTO);
    void deleteItem(Long id);
    Item updateItem(Long id, ItemDTO itemDTO);
    PurchaseData buyItem(Long id, int quantity);

}
