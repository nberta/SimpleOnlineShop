package edu.miu.simpleshop.repository;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.CartItem;
import edu.miu.simpleshop.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends CrudRepository <ShoppingCart, Long>{

    ShoppingCart findShoppingCartByBuyer(Buyer buyer);
    ShoppingCart findShoppingCartByBuyerAndCartItems (Buyer buyer, CartItem cartItem);

}
