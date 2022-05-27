package com.revature.customPaint.services;

import com.revature.customPaint.daos.ProductDAO;
import com.revature.customPaint.models.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void register(Product product) {
        productDAO.save(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }
}
