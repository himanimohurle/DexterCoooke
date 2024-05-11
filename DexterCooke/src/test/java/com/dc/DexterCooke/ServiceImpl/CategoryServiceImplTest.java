package com.dc.DexterCooke.ServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dc.DexterCooke.Model.Category;
import com.dc.DexterCooke.Repository.CategoryRepository;
import com.dc.DexterCooke.ServiceInterface.CategoryService;
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryRepository cr;
	@InjectMocks
	CategoryServiceImpl cs;
	 @Test
     void findAll_should_return_category_list() {
         // Given
        
         // When
         when(cr.findAll()).thenReturn(Arrays.asList(new Category(1l,"Furniture","Category Description"),new Category(2l,"ElectronicDevice","Category Description")));
         List<Category> category = this.cs.getAllCategories();
         // Then
         assertEquals(2, category.size());
         verify(this.cr).findAll();
     }
	 

	 @Test
	 void findById_should_return_category() {
	     when(cr.findById(Long.valueOf(11))).thenReturn(Optional.of(new Category(11L, "Furniture", "Category Description")));
	     Optional<Category> returnedCategory = Optional.of(this.cs.getCategoryById(11L));

	     // Then
	     assertTrue(returnedCategory.isPresent()); // Ensure Optional contains a value
	     assertEquals(11L, returnedCategory.get().getId());
	     verify(cr, times(1)).findById(Long.valueOf(11));
	 }
	 
	 @Test
	 void save_should_insert_new_category() {
	     // When
	     Category category = new Category(); // Creating a new instance directly
	     this.cs.createCategory(category);
	     // Then
	     verify(this.cr).save(category);
	 }
	 @Test
	    void updateCategory_should_update_category() {
	        // Given
	        Long categoryId = 1L;
	        Category existingCategory = new Category(categoryId, "Furniture", "Category Description");
	        Category updatedCategory = new Category(categoryId, "Electronics", "Updated Category Description");
	        
	        when(cr.findById(categoryId)).thenReturn(Optional.of(existingCategory));
	        when(cr.save(existingCategory)).thenReturn(updatedCategory);
	        
	        // When
	        Category result = this.cs.updateCategory(categoryId, updatedCategory);
	        
	        // Then
	        assertEquals(updatedCategory, result);
	        assertEquals("Electronics", result.getName());
	        assertEquals("Updated Category Description", result.getDescription());
	        verify(cr).findById(categoryId);
	        verify(cr).save(existingCategory);
	    }
	 
	 @Test
     void deleteById_should_delete_category() {
		 Category category = new Category(11L, "Furniture", "Category Description");
		    when(cr.findById(11L)).thenReturn(Optional.of(category));
		    
		    // When
		    this.cs.deleteCategory(11L);
		    
		    // Then
		    verify(this.cr).delete(category);
     }
	 
	 
}
