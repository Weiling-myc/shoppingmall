package com.bjfu.entity.vo;

import com.bjfu.entity.Product;

import java.io.Serializable;
import java.util.List;

public class MerchandiseMenuVO implements Serializable {
    private String categoryName;
    private Integer categoryId;
    private List<Product> products;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
