package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.ShoppingCart;

public interface BuyerService {

    Buyer delete(Long id);
    Buyer save(Buyer buyer);
    Buyer getById(Long id);
    ShoppingCart getShoppingCart(Buyer buyer);
    Buyer buyerFollowSeller(Buyer buyer, Long sellerId);
}
