package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.repository.BuyerRepository;
import edu.miu.simpleshop.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository repository;


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
    public ShoppingCart getShoppingCart(Buyer buyer) {
        return null;
    }


    @Override
    public void followSeller(Buyer buyer, Seller seller) {
        buyer.followSeller(seller);
    }

    @Override
    public void unfollowSeller(Buyer buyer, Seller seller) {
        buyer.unfollowSeller(seller);
    }
}
