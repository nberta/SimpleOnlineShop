package edu.miu.simpleshop.domain;

import javax.persistence.*;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Buyer buyer;

    private String review;
    private int rating;
    private Boolean isConfirmed;
    private boolean enabled;

    public ProductReview(){}

    public ProductReview(Product product, Buyer buyer, String review, int rating) {
        this.product = product;
        this.buyer = buyer;
        this.review = review;
        this.rating = rating;
    }

    public Long getId() { return this.id; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }


    public boolean isEnabled() {
        return enabled;
    }
}
