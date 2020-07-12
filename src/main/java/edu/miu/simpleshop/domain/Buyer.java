package edu.miu.simpleshop.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 10)
public class Buyer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Valid
    @OneToOne
    private User user;

    private int gainPoints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    private Address shippingAddress;

    @OneToOne
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private List<ProductReview> productReviews = new ArrayList<>();

    @OneToOne
    private ShoppingCart shoppingCart;

    public Buyer() {
    }

    public Long getId() { return this.id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGainPoints() {
        return gainPoints;
    }

    public void setGainPoints(int gainPoints) {
        this.gainPoints = gainPoints;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
