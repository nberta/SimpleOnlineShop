package edu.miu.simpleshop.domain;


import edu.miu.simpleshop.exception.IllegalProductStateException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=2, max=50, message = "{product.name}")
    private String name;

    @NotBlank
    private String description;

    @Min(1)
    private int quantity;

    private boolean enabled = false;

    @Min(0)
    private int price;

    @Transient
    private MultipartFile productImage;

    private String imageIdentifier;

    private Boolean isConfirmed = false;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductReview> productReviews = new ArrayList<>();

    @OneToOne (cascade = CascadeType.PERSIST)
    private Seller seller;

    //Added later
    @ManyToOne
    private Category category;

    public Product() {
    }

    //Skipped multipartfile for now
    public Product(String name, String description, int quantity, boolean enabled, int price,  Category category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.enabled = enabled;
        this.price = price;
        this.category = category;
    }

    public Long getId() { return this.id; }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    public void setImageIdentifier(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAvailable() { return quantity > 0; }

    public void decrementQuantity(int quantity) {
        if (quantity > 0) this.quantity -= quantity;
        if (quantity < 0) throw new IllegalProductStateException();
    }
}
