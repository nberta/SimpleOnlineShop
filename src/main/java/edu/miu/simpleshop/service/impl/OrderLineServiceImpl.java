package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.enums.OrderStatus;
import edu.miu.simpleshop.exception.IllegalAccessAttemptException;
import edu.miu.simpleshop.exception.IllegalOrderStateException;
import edu.miu.simpleshop.repository.OrderLineRepository;
import edu.miu.simpleshop.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public OrderLine getById(Long id) {
        return orderLineRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    @Override
    public OrderLine updateToShipped(Long id, Seller seller) {
        OrderLine orderLine = getById(id);
        if (!orderLine.getSeller().getId().equals(seller.getId()))
            throw new IllegalAccessAttemptException("You're not allowed to access this order");
        if(orderLine.getStatus().equals(OrderStatus.CREATED)) {
            orderLine.setStatus(OrderStatus.SHIPPED);
            return save(orderLine);
        } else throw new IllegalOrderStateException("Order has already been handled, and cannot be shipped.");
    }

    @Override
    public Collection<OrderLine> findAllByOrderId(Long id) {
        return orderLineRepository.findAllByOrderId(id);
    }
}
