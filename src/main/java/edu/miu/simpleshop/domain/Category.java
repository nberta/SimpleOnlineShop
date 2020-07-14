package edu.miu.simpleshop.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 10)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany (cascade = CascadeType.PERSIST, mappedBy = "category")
    private List<Product> products = new ArrayList<>();


    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public Long getId() { return this.id; }

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
