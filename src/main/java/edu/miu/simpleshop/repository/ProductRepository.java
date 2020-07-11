package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{

    List<Product> findBySellerID(Long Id);

    Collection<Product> findByCategoryId(Long Id);

}
