package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.CartItem;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface BuyerService {

    Buyer delete(Long id);
    Buyer save(Buyer buyer);
    Buyer getById(Long id);
    ShoppingCart saveShoppingCart(Buyer buyer);
    CartItem removeCartItem(Buyer buyer, Long id);
    void followSeller(Buyer buyer, Long sellerId);
    void unfollowSeller (Buyer buyer, Long sellerId);
    Collection<Seller> getFollowedSellersForBuyer(Long id);
    List<Buyer> getPendingBuyers();
    Buyer update(Buyer buyer, Long id);


}
