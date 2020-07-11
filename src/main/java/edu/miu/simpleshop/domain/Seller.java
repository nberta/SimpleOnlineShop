package edu.miu.simpleshop.domain;

import javax.validation.constraints.NotNull;
import java.util.List;


public class Seller {
    private Long id;

    @NotNull
    private List<Product> products;

    @NotNull
    private List<OrderLine> orderLines;

    @NotNull
    private User user;

    public Seller(){}

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
