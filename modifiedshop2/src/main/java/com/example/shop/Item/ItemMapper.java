package com.example.shop.Item;

import com.example.shop.Category.CategoryEntity;
import com.example.shop.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public Item entityToDomainModelForItem(ItemEntity itemEntity){
        Item item= new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setPrice(itemEntity.getPrice());
        item.setStockQuantity(itemEntity.getStockQuantity());
        item.setCategoryId(itemEntity.getCategoryEntity().getId());
        return item;

    }

    public ItemEntity DomainModelToEntityForItem(Item item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStockQuantity(item.getStockQuantity());

        if(item.getCategoryId() != null){
            CategoryEntity categoryEntity = categoryRepository.findById(item.getCategoryId()).orElseThrow(()->
                    new RuntimeException("Category not found"));
            itemEntity.setCategoryEntity(categoryEntity);
        }


        return itemEntity;
    }



}
