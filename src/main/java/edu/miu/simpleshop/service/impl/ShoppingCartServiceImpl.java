package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.CartItem;
import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.repository.CartItemRepository;
import edu.miu.simpleshop.repository.ShoppingCartRepository;
import edu.miu.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart getById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart delete(Long id) {
        ShoppingCart shoppingCart = getById(id);
        shoppingCartRepository.delete(shoppingCart);
        return shoppingCart;
    }

    @Override
    public CartItem removeCartItem(Long cartItemId, Buyer buyer) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        buyer.getShoppingCart().getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCartRepository.save(buyer.getShoppingCart());
        return cartItem;
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        for (CartItem c : shoppingCart.getCartItems())
            cartItemRepository.delete(c);
        shoppingCart.clear();
        shoppingCartRepository.save(shoppingCart);
    }
}
