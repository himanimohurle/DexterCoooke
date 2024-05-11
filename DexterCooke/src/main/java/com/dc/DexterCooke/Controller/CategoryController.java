package com.dc.DexterCooke.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.DexterCooke.Model.Category;
import com.dc.DexterCooke.ServiceInterface.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/get")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/get/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/post")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long id,@RequestBody Category categoryDetails) {
        return categoryService.updateCategory(id, categoryDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
        
    }
	

}
