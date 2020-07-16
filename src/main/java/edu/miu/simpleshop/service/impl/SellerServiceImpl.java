package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.domain.enums.OrderStatus;
import edu.miu.simpleshop.exception.ForbiddenAccessException;
import edu.miu.simpleshop.exception.IllegalOrderStateException;
import edu.miu.simpleshop.repository.OrderLineRepository;
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

    @Autowired
    private OrderLineRepository orderLineRepository;

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

    @Override
    public boolean cancelOrderLineForSeller(Long id, Seller seller) {
        OrderLine orderLine = orderLineRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!seller.getId().equals(orderLine.getSeller().getId()))
            throw new ForbiddenAccessException();
        if(orderLine.getStatus().equals(OrderStatus.CREATED)) {
            orderLine.setStatus(OrderStatus.CANCELLED);
            orderLineRepository.save(orderLine);
            return true;
        } else throw new IllegalOrderStateException("Order has already been handled, and cannot be shipped.");
    }

    @Override
    public Seller approve(Long id) {
        Seller seller = getById(id);
        seller.setIsActive(true);
        save(seller);
        return seller;
    }

}
