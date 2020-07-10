package edu.miu.simpleshop.domain;

import edu.miu.simpleshop.domain.enums.OrderStatus;

public class OrderLine {
    private Long id;
    private Seller seller;
    private Product product;
    private Order order;
    private int quantity;
    private OrderStatus status = OrderStatus.CREATED;

    private OrderLine(){}

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
