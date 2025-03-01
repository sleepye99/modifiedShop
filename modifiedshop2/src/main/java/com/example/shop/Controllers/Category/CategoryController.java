package com.example.shop.Controllers.Category;


import com.example.shop.DTOs.CategoryDTO;
import com.example.shop.DomainModels.Category;
import com.example.shop.DomainModels.Item;
import com.example.shop.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//all the operations are within the range of /categories.
@RequestMapping("/categories")
public class CategoryController {
    //dependency injection of Service class
    @Autowired
    private CategoryService categoryService;

    // Plain GET Method displays all present categories

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // displays specific category and its items.
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    // displays items by their categories
    @GetMapping("/{id}/items")
    public List<Item> getItemByCategory(@PathVariable Long id){
        return categoryService.getItemByCategory(id);
    }




    // POST method here adds a new category
    @PostMapping
    public Category addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    //deletes category with respect to its id.
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
    //PUT method updates category.
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
    }
}