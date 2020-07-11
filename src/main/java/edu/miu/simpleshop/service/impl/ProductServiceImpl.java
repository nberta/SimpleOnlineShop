package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Collection<Product> save(Collection<Product> products) {
        return null;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllUnconfirmedProducts() {
        return null;
    }

    @Override
    public List<Product> getBySellerId(Long id) {
        return null;
    }

    @Override
    public Collection<Product> getByCategoryId(Long id) {
        return null;
    }
}
