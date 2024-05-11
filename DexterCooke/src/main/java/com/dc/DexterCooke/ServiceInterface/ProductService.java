package com.dc.DexterCooke.ServiceInterface;

import java.util.List;

import com.dc.DexterCooke.Model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Long id);

	

	void deleteProduct(Long id);

	Product updateProduct(Long id, Product productDetails);

	Product createProduct(Product product, long categoryId);

}
