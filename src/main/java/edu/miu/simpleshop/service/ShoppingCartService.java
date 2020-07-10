package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getById(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
    ShoppingCart delete(Long id);

}
