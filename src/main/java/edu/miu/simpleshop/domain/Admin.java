package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Admin(){}

    public Admin(User user){
        this.user = user;
    }


    public Long getId() { return this.id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

