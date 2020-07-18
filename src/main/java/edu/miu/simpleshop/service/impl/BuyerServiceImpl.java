
package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.repository.BuyerRepository;
import edu.miu.simpleshop.repository.FollowRepository;
import edu.miu.simpleshop.repository.SellerRepository;
import edu.miu.simpleshop.repository.ShoppingCartRepository;
import edu.miu.simpleshop.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public Buyer delete(Long id) {
        Buyer buyer = getById(id);
        buyerRepository.delete(buyer);
        return buyer;
    }

    @Override
    public Buyer save(Buyer buyer) {
        buyer.getUser().setRole(Role.BUYER);
       return buyerRepository.save(buyer);
    }

    @Override
    public Buyer getById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ShoppingCart getShoppingCartForBuyer(Buyer buyer) {
        buyer = buyerRepository.findById(buyer.getId()).orElseThrow(EntityNotFoundException::new);
        return buyer.getShoppingCart();
    }

    @Override
    public Buyer update(Buyer buyer, Long id) {
        Buyer oldBuyer = getById(id);
        oldBuyer.getUser().setUsername(buyer.getUser().getUsername());
        oldBuyer.getUser().setPassword(buyer.getUser().getPassword());
        oldBuyer.getUser().setEmail(buyer.getUser().getEmail());
        return buyerRepository.save(oldBuyer);
    }

    @Override
    public Buyer getByUser(User user) {
        return buyerRepository.findByUserId(user.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Buyer approve(Long id) {
        Buyer buyer = getById(id);
        buyer.setIsActive(true);
        save(buyer);
        return buyer;
    }

    @Override
    public void followSeller(Buyer buyer, Long sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        seller.ifPresent(buyer::followSeller);
    }

    @Override
    public void unfollowSeller(Buyer buyer, Long sellerId) {
        buyer.unfollowSeller(sellerId);
        save(buyer);
    }

    @Override
    public Collection<Seller> getFollowedSellersForBuyer(Long id) {
        Collection<Follow> follows = followRepository.findAllByBuyerId(id);
        return follows.stream().map(Follow::getSeller).collect(Collectors.toList());
    }

    @Override
    public ShoppingCart saveShoppingCart(Buyer buyer) {
        return shoppingCartRepository.save(buyer.getShoppingCart());
    }

    @Override
    public void removeCartItem(Buyer buyer, Long id) {
        ShoppingCart shoppingCart = buyer.getShoppingCart();
        shoppingCart.removeCartItemById(id);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<Buyer> getPendingBuyers() {
        return buyerRepository.findAllByIsActiveFalse();
    }
}

