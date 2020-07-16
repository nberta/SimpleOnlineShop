package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.*;

import java.util.Collection;
import java.util.List;

public interface BuyerService {

    Buyer delete(Long id);
    Buyer save(Buyer buyer);
    Buyer getById(Long id);
    ShoppingCart getShoppingCartForBuyer(Buyer buyer);
    ShoppingCart saveShoppingCart(Buyer buyer);
    CartItem removeCartItem(Buyer buyer, Long id);
    void followSeller(Buyer buyer, Long sellerId);
    void unfollowSeller (Buyer buyer, Long sellerId);
    Collection<Seller> getFollowedSellersForBuyer(Long id);
    List<Buyer> getPendingBuyers();
    Buyer update(Buyer buyer, Long id);
    Buyer getByUser(User user);
    Buyer approve(Long id);


}
