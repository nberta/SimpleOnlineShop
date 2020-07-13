package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToMany (cascade = CascadeType.ALL)
    private List<@NotNull Product> products = new ArrayList<>();

    @NotNull
    @OneToMany (cascade = CascadeType.PERSIST, mappedBy = "seller")
    private List<@NotNull OrderLine> orderLines = new ArrayList<>();

    @Valid
    @OneToOne (cascade = CascadeType.ALL)
    private User user;

    private boolean isActive;

    public Seller(){}

    public Long getId() { return this.id; }

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

    public boolean isActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}
