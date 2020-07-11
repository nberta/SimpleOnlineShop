package edu.miu.simpleshop.domain;

import javax.persistence.*;

@Entity
public class Follow {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;

    public Follow(){}

    public Long getId() { return this.id; }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
