package edu.miu.simpleshop.domain;

import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<CartItem> cartItems;
    private Buyer buyer;

    public ShoppingCart(){}

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
