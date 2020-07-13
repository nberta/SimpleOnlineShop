package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, Long> {

    List<ProductReview> findAllByEnabledFalse();
    //Collection<ProductReview> findByCategoryId(Long Id);
}

