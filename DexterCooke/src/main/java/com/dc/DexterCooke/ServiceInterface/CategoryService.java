package com.dc.DexterCooke.ServiceInterface;

import java.util.List;

import com.dc.DexterCooke.Model.Category;

public interface CategoryService {

	List<Category> getAllCategories();

	Category getCategoryById(Long id);

	Category createCategory(Category category);

	Category updateCategory(Long id, Category categoryDetails);


	

	void deleteCategory(Long id);

	

}
