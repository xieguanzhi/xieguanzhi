package com.xieguanzhi.service.impl;

import com.xieguanzhi.dao.ProductDao;
import com.xieguanzhi.domain.Product;
import com.xieguanzhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productDao.save(product);
    }

}
