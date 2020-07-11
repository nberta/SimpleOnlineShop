package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Valid
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    public Admin(){}

    public Long getId() { return this.id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

