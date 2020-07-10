package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    Product getById(Long id);
    Product save(Product product);
    Collection<Product> save(Collection<Product> products);
    Product delete(Long id);
    Product getAllUnconfirmedProducts();
    List<Product> getBySellerId(Long id);
    Collection<Product> getByCategoryId(Long id);

}
