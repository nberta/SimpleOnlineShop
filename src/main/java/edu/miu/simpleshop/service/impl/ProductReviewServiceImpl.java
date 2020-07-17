package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;
import edu.miu.simpleshop.repository.ProductReviewRepository;
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
    private ProductReviewRepository productReviewRepository;



    @Override
    public ProductReview getById(Long id) {
        ProductReview productReview = productReviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return productReview;
    }

    @Override
    public ProductReview save(ProductReview productReview) {
        ProductReview review = productReviewRepository.save(productReview);
        return review;
    }

    @Override
    public Collection<ProductReview> save(Collection<ProductReview> reviews) {
        Collection<ProductReview> result = productReviewRepository.saveAll(reviews);
        return result;
    }

    @Override
    public ProductReview delete(Long id) {
        ProductReview review = productReviewRepository.findById(id).orElseThrow();
        productReviewRepository.delete(review);
        return review;
    }

    @Override
    public ProductReview confirm(Long id) {
        ProductReview productReview = productReviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productReview.setConfirmed(true);
        productReviewRepository.save(productReview);
        return productReview;
    }

    @Override
    public List<ProductReview> getAllUnconfirmedReviews() {
        return productReviewRepository.findAllByIsConfirmedFalse();
    }

    @Override
    public List<ProductReview> getAllReviewsFor(Product product) {
        return productReviewRepository.findAllByProductId(product.getId());
    }
}
