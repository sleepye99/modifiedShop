package com.example.shop.Category;

import com.example.shop.Item.Item;

import java.util.List;

public interface CategoryUseCase {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    List<Item> getItemByCategory(Long id);
    Category addCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category updateCategory(Long id, CategoryDTO categoryDTO);

}
