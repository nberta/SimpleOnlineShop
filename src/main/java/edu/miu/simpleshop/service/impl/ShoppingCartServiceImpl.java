package edu.miu.simpleshop.service.impl;


import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.repository.ShoppingCartRepository;
import edu.miu.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart getById(Long id) {
        return shoppingCartRepository.findById(id).
                orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart delete(Long id){
        ShoppingCart shoppingCart = getById(id);
        shoppingCartRepository.delete(shoppingCart);
        return shoppingCart;

        }
}
