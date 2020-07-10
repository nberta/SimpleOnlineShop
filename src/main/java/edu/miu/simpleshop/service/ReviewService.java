package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;

import java.util.Collection;
import java.util.List;

public interface ReviewService {

    ProductReview getById(Long id);
    ProductReview save(ProductReview productReview);
    Collection<Product> save(Collection<ProductReview> reviews);
    ProductReview delete(ProductReview productReview);
    List<ProductReview> getAllUnconfirmedReviews();
    List<ProductReview> getAllReviewsFor(Product product);

}
