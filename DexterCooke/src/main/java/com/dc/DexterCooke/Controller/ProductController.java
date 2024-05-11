package com.dc.DexterCooke.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.DexterCooke.Model.Product;
import com.dc.DexterCooke.ServiceInterface.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired 
	ProductService productService;

    @GetMapping("/get")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable(value = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/save/{categoryId}")
    public Product createProduct(@RequestBody Product product,@PathVariable long categoryId) {
        return productService.createProduct(product,categoryId);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long id,@RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
       
    }

}
