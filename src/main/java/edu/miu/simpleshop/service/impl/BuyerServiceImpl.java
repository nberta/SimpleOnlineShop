package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.CartItem;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.repository.BuyerRepository;
import edu.miu.simpleshop.repository.ShoppingCartRepository;
import edu.miu.simpleshop.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository repository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public Buyer delete(Long id) {
        Buyer buyer = getById(id);
        repository.delete(buyer);
        return buyer;
    }

    @Override
    public Buyer save(Buyer buyer) {
       return repository.save(buyer);
    }

    @Override
    public Buyer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public void followSeller(Buyer buyer, Seller seller) {
        buyer.followSeller(seller);
    }

    @Override
    public void unfollowSeller(Buyer buyer, Seller seller) {
        buyer.unfollowSeller(seller);
    }



    @Override
    public ShoppingCart saveShoppingCart(Buyer buyer) {
        return shoppingCartRepository.save(buyer.getShoppingCart());
    }

    @Override
    public CartItem removeCartItem(Buyer buyer, Long id) {
        ShoppingCart shoppingCart = buyer.getShoppingCart();
        CartItem cartItem = shoppingCart.removeCartItemById(id);
        shoppingCartRepository.save(shoppingCart);
        return cartItem;
    }
}
