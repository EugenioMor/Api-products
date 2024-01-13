package com.productsmanagement.service;


import com.productsmanagement.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> productsList();

    public void saveProduct(Product product);

    public Product getProductById(Long id);

    public void deleteProduct(Long id);
}
