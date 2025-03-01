package com.example.shop.Services;


import com.example.shop.DTOs.CategoryDTO;
import com.example.shop.DomainModels.Category;
import com.example.shop.DomainModels.Item;
import com.example.shop.Entities.CategoryEntity;
import com.example.shop.Mappers.CategoryMapper;
import com.example.shop.Repositories.CategoryRepository;
import com.example.shop.UseCases.CategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//This class performs service operations of Category.
@Service
public class CategoryService implements CategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    //Service Layer of the app should map between the Domain model and Entity

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> getAllCategories() {

        return categoryRepository.findAll().stream().
                map(categoryMapper::entityToDomainModelForCategory).
                collect(Collectors.toList());

    }

    public Category getCategoryById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Could not find any category with given id."));
        return categoryMapper.entityToDomainModelForCategory(categoryEntity);
    }

    public List<Item> getItemByCategory(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Could not find any category within given id"));
        Category category = categoryMapper.entityToDomainModelForCategory(categoryEntity);
        return category.getItems();
    }

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        CategoryEntity categoryEntity = categoryMapper.domainModelToEntityForCategory(category);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);

        return categoryMapper.entityToDomainModelForCategory(savedCategoryEntity);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryEntity.setName(categoryDTO.getName());

        CategoryEntity updatedCategoryEntity = categoryRepository.save(categoryEntity);



        return categoryMapper.entityToDomainModelForCategory(updatedCategoryEntity);
    }
}