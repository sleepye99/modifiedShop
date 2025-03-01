package com.example.shop.Controllers.Item;

import com.example.shop.DTOs.ItemDTO;
import com.example.shop.DomainModels.Item;
import com.example.shop.DomainModels.PurchaseData;
import com.example.shop.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item addItem(@RequestBody ItemDTO itemDTO) {
        return itemService.addItem(itemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(id,itemDTO);
    }

    @PostMapping("/{id}/buy")
    public PurchaseData buyItem(@PathVariable Long id, @RequestParam int quantity){
        return itemService.buyItem(id,quantity);
    }
}