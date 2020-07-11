package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.repository.CategoryRepository;
import edu.miu.simpleshop.repository.ProductRepository;
import edu.miu.simpleshop.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Collection<Product> save(Collection<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product delete(Long id) {
//        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return null;
    }

    @Override
    public List<Product> getAllUnconfirmedProducts() {
        List<Product> result;
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

    @Override
    public Product getProduct(Long id) {
        return null;
    }
}
