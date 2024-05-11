package com.dc.DexterCooke.ServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dc.DexterCooke.Model.Category;
import com.dc.DexterCooke.Model.Product;
import com.dc.DexterCooke.Repository.CategoryRepository;
import com.dc.DexterCooke.Repository.ProductRepository;

class ProductServiceImplTest {

    @Mock
    ProductRepository pr;
    @Mock
    CategoryRepository cr; // Adding CategoryRepository mock
    @InjectMocks
    ProductServiceImpl ps;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    void findAll_should_return_product_list() {
    Date createdAt = new Date(System.currentTimeMillis());
    Date updatedAt = new Date(System.currentTimeMillis());
    	 when(pr.findAll()).thenReturn(Arrays.asList(
    		        new Product(1L, "Table", "Product Description", 1L, new Category(1L, "Furniture", "Furniture Description"),createdAt, updatedAt),
    		        new Product(2L, "Phone", "Product Description", 2L, new Category(2L, "Electronics", "Electronics Description"),createdAt, updatedAt)
    		    ));
    		    List<Product> products = this.ps.getAllProducts();
    		    // Then
    		    assertEquals(2, products.size());
    		    verify(this.pr).findAll();
    }
    
    @Test
    void findById_should_return_product() {
    	Date createdAt = new Date(System.currentTimeMillis());
        Date updatedAt = new Date(System.currentTimeMillis());
    	 when(pr.findById(1L)).thenReturn(Optional.of(new Product(1L, "Table", "Product Description", 1L, new Category(1L, "Furniture", "Furniture Description"),createdAt, updatedAt)));
    	    
    	    Optional<Product> returnedProduct = Optional.of(this.ps.getProductById(1L));

    	    // Then
    	    assertTrue(returnedProduct.isPresent()); // Ensure Optional contains a value
    	    assertEquals(1L, returnedProduct.get().getId());
    	    
    	    assertEquals(createdAt, returnedProduct.get().getCreatedAt()); // Verify createdAt
    	    assertEquals(updatedAt, returnedProduct.get().getUpdatedAt());
    	    verify(pr, times(1)).findById(1L);
    }

    @Test
    void save_should_insert_new_product() {
        // When
        Product product = new Product(); // Creating a new instance directly
        this.ps.createProduct(product, 11L);
        // Then
        verify(this.pr).save(product);
    }
//
    @Test
    void updateProduct_should_update_product() {
        // Given
    	Date createdAt = new Date(System.currentTimeMillis());
        Date updatedAt = new Date(System.currentTimeMillis());
//        Long productId = 1L;
        Product existingProduct = new Product(1L, "Table", "Product Description", 1L, new Category(1L, "Furniture", "Furniture Description"),createdAt, updatedAt);
        Product updatedProduct = new Product(1L, "Desk", "Updated Product Description", 1L,new Category(1L, "Furniture", "Furniture Description"),createdAt, updatedAt);
        
        when(pr.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(pr.save(existingProduct)).thenReturn(updatedProduct);
        
        // When
        Product result = this.ps.updateProduct(1L, updatedProduct);
        
        // Then
        assertEquals(updatedProduct, result);
        assertEquals("Desk", result.getName());
        assertEquals("Updated Product Description", result.getDescription());
        verify(pr).findById(1L);
        verify(pr).save(existingProduct);
    }

    @Test
    void deleteById_should_delete_product() {
    Date createdAt = new Date(System.currentTimeMillis());
    Date updatedAt = new Date(System.currentTimeMillis());
        Product product =  new Product(1L, "Table", "Product Description", 1L, new Category(1L, "Furniture", "Furniture Description"),createdAt, updatedAt);
        when(pr.findById(11L)).thenReturn(Optional.of(product));
        
        // When
        this.ps.deleteProduct(11L);
        
        // Then
        verify(this.pr).delete(product);
    }
    

	 

    
}
