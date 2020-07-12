package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToMany (cascade = CascadeType.ALL)
    private List<@NotNull Product> products;

    @NotNull
    @OneToMany (cascade = CascadeType.PERSIST, mappedBy = "seller")
    private List<@NotNull OrderLine> orderLines;

    @Valid
    @OneToOne (cascade = CascadeType.ALL)
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
