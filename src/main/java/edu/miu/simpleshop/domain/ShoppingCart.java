package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToMany (fetch = FetchType.EAGER, orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<@NotNull CartItem> cartItems = new ArrayList<>();

    @OneToOne
    private Buyer buyer;

    public ShoppingCart(){  }
    public ShoppingCart(List<CartItem> cartItems){
        this.cartItems = cartItems;
       // for (CartItem c : cartItems) c.setShoppingCart(this);
    }

    public Long getId() { return this.id; }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        //cartItem.setShoppingCart(this);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public CartItem removeLastCartItem() {
        if (!cartItems.isEmpty()) return null;
        else return cartItems.remove(cartItems.size() - 1);
    }

    public CartItem removeCartItemById(Long id) {
        for (CartItem c : cartItems)
            if (c.getId().equals(id)) return c;
            return null;
    }

    public void clear() {
        int size = cartItems.size();
        while (size-- > 0)
            cartItems.remove(size);
    }
}
