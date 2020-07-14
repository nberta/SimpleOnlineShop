package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;
import edu.miu.simpleshop.repository.ReviewRepository;
import edu.miu.simpleshop.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ReviewRepository reviewRepository;



    @Override
    public ProductReview getById(Long id) {
        ProductReview productReview = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return productReview;
    }

    @Override
    public ProductReview save(ProductReview productReview) {
        ProductReview review = reviewRepository.save(productReview);
        return review;
    }

    @Override
    public Collection<ProductReview> save(Collection<ProductReview> reviews) {
        Collection<ProductReview> result = reviewRepository.saveAll(reviews);
        return result;
    }

    @Override
    public ProductReview delete(Long id) {
        ProductReview review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
        return review;
    }

    @Override
    public List<ProductReview> getAllUnconfirmedReviews() {
        List<ProductReview> review = reviewRepository.getAllUnconfirmedReviews();
        return review;

    }

    @Override
    public List<ProductReview> getAllReviewsFor(Product product) {
        List<ProductReview> review = reviewRepository.getAllReviewsFor(product);
        return review;
    }
}
