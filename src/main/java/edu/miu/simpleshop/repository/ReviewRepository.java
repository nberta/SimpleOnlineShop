package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, Long> {

    //Collection<ProductReview> findByCategoryId(Long Id);
}

