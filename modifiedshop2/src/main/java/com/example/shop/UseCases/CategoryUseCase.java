package com.example.shop.UseCases;



import com.example.shop.DTOs.CategoryDTO;
import com.example.shop.DomainModels.Category;
import com.example.shop.DomainModels.Item;

import java.util.List;

public interface CategoryUseCase {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    List<Item> getItemByCategory(Long id);
    Category addCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category updateCategory(Long id, CategoryDTO categoryDTO);

}