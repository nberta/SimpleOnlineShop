package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.repository.ShoppingCartRepository;
import edu.miu.simpleshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart getById(Long id) {
        return null;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public ShoppingCart delete(Long id){
        return null;


        }
}
