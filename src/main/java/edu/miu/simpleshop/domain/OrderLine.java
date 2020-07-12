package edu.miu.simpleshop.domain;

import edu.miu.simpleshop.domain.enums.OrderStatus;

import javax.persistence.*;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Seller seller;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;

    private int quantity;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    public OrderLine(){}

    public Long getId() { return this.id; }

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
