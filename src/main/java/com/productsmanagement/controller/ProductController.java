package com.productsmanagement.controller;

import com.productsmanagement.model.Product;
import com.productsmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> productsList(){
        return productService.productsList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try {
            Product prod = productService.getProductById(id);
            return new ResponseEntity<Product>(prod, HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id){
        try {
            Product existentProd = productService.getProductById(id);

            existentProd.setName(product.getName());
            existentProd.setPrice(product.getPrice());

            productService.saveProduct(existentProd);
            return new ResponseEntity<Product>(HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
