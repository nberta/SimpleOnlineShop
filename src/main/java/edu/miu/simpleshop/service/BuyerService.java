package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.ShoppingCart;

public interface BuyerService {

    Buyer delete(Long id);
    Buyer save(Buyer buyer);
    Buyer getById(Long id);
    ShoppingCart getShoppingCart(Buyer buyer);
    void followSeller(Buyer buyer, Seller seller);
    void (Buyer buyer, Seller seller);




}
