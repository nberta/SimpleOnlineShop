package edu.miu.simpleshop.domain;

import java.util.List;

public class Category {
    private Long id;
    private String name;
    private List<Product> products;

    public Category(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
