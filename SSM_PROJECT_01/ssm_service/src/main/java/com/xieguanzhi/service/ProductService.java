package com.xieguanzhi.service;

import com.xieguanzhi.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product>findAll();

    void saveProduct(Product product);
}
