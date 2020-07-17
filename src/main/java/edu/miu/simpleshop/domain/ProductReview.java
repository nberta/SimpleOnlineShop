package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Buyer buyer;

    /*@Column(name = "comment_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime commentTime = LocalDateTime.now();*/

    @NotBlank
    private String review;

    @Min(value = 1, message = "Rating should be between 1 and 5")
    @Max(value = 5, message = "Rating should be between 1 and 5")
    private int rating;

    private Boolean isConfirmed = false;
    private boolean enabled = false;

    public ProductReview() {
    }

    public ProductReview(Product product, Buyer buyer, String review){}

    public ProductReview(Product product, Buyer buyer, String review, int rating) {
        this.product = product;
        this.buyer = buyer;
        this.review = review;
        this.rating = rating;
        product.addReview(this);
    }

    public Long getId() {
        return this.id;
    }

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

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", review='" + review + '\'' +
                '}';
    }
}

