package edu.miu.simpleshop.domain;


import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Product product;

    private int quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;

    public CartItem(){}

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() { return this.id; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
