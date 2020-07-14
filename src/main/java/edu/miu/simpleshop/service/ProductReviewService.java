package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;

import java.util.Collection;
import java.util.List;

public interface ProductReviewService {

    ProductReview getById(Long id);
    ProductReview save(ProductReview productReview);
    Collection<ProductReview> save(Collection<ProductReview> reviews);
    ProductReview delete(Long id);

    //List<ProductReview> getAllUnconfirmedReviews();
    //List<ProductReview> getAllReviewsFor(Product product);

}
