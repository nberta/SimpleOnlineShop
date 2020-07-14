package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.repository.ProductRepository;
import edu.miu.simpleshop.repository.SellerRepository;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller delete(Long id) {
        Seller seller = getById(id);
        sellerRepository.delete(seller);
        return seller;
    }

    @Override
    public Seller getById(Long id) {
        return sellerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Seller> getPendingSellers() {
        return sellerRepository.findAllByIsActiveFalse();
    }

    @Override
    public void notifySellers(List<OrderLine> orderLines) {
        orderLines.forEach(orderLine -> {
            Product product = orderLine.getProduct();
            product.decrementQuantity(orderLine.getQuantity());
            productRepository.save(product);
            Seller seller = product.getSeller();
            seller.addOrderLine(orderLine);
            sellerRepository.save(seller);
        });
    }

    @Override
    public Seller getByUser(User user) {
        return sellerRepository.findByUserId(user.getId()).orElseThrow(EntityNotFoundException::new);
    }

}
