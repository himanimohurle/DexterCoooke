package com.dc.DexterCooke.ServiceImpl;



import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.DexterCooke.Exception.ResourceNotFoundException;
import com.dc.DexterCooke.Model.Category;
import com.dc.DexterCooke.Repository.CategoryRepository;
import com.dc.DexterCooke.ServiceInterface.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	 @Autowired
	 CategoryRepository categoryRepository;

	  @Override
	    public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    @Override
	    public Category getCategoryById(Long id) {
	        return categoryRepository.findById(id)
	                                  .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
	    }

	    @Override
	    public Category createCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    @Override
	    public Category updateCategory(Long id, Category categoryDetails) {
	        Category category = categoryRepository.findById(id)
	                                              .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
	        category.setName(categoryDetails.getName());
	        category.setDescription(categoryDetails.getDescription());
	        return categoryRepository.save(category);
	    }

	    

	    @Override
	    public void deleteCategory(Long id) {
	        Category category = categoryRepository.findById(id)
	                                              .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
	        categoryRepository.delete(category);
	    }
	    
	    
	}
	