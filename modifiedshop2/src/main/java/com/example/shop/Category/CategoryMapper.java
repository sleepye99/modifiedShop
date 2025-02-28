package com.example.shop.Category;


import com.example.shop.Item.Item;
import com.example.shop.Item.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CategoryMapper {

    @Autowired
    private ItemMapper itemMapper;

    public Category entityToDomainModelForCategory(CategoryEntity categoryEntity){
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());

        if(categoryEntity.getItems()!= null){
            List<Item> itemList = categoryEntity.getItems().stream().
                    map(itemMapper::entityToDomainModelForItem).collect(Collectors.toList());
            category.setItems(itemList);
        }

        return category;
    }
    public CategoryEntity domainModelToEntityForCategory(Category category){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());


        return categoryEntity;
    }
}
