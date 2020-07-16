package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    List<ProductReview> findAllByIsConfirmedFalse();

    Collection<ProductReview> findByBuyerId(Long Id);

    List<ProductReview> findAllByProductId(Long id);
}
