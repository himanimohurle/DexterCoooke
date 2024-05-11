package com.dc.DexterCooke.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.DexterCooke.Exception.ResourceNotFoundException;
import com.dc.DexterCooke.Model.Category;
import com.dc.DexterCooke.Model.Product;
import com.dc.DexterCooke.Repository.CategoryRepository;
import com.dc.DexterCooke.Repository.ProductRepository;
import com.dc.DexterCooke.ServiceInterface.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	 @Autowired
	 ProductRepository productRepository;
	 @Autowired
	 CategoryRepository cr;

	    @Override
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @Override
	    public Product getProductById(Long id) {
	        return productRepository.findById(id)
	                                 .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
	    }

	    @Override
		public Product createProduct(Product product, long categoryId) {
			                  Optional<Category> op= cr.findById(categoryId);
			                   if(op.isPresent()) {
			                	   Category c=op.get();
			                	   product.setCategory(c);
			         
			                   }
			return productRepository.save(product);
		}

	    @Override
	    public Product updateProduct(Long id, Product productDetails) {
	        Product product = productRepository.findById(id)
	                                           .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
	        product.setName(productDetails.getName());
	        product.setDescription(productDetails.getDescription());
	        product.setPrice(productDetails.getPrice());

	        if (productDetails.getCategory() != null) {
	            product.setCategory(productDetails.getCategory());
	        }
	        return productRepository.save(product);
	    }

	    @Override
	    public void deleteProduct(Long id) {
	        Product product = productRepository.findById(id)
	                                           .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
	        productRepository.delete(product);
	    }

		

}
