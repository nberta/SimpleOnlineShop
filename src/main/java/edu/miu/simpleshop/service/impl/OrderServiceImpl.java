package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.exception.IllegalCustomerStateException;
import edu.miu.simpleshop.repository.OrderLineRepository;
import edu.miu.simpleshop.repository.OrderRepository;
import edu.miu.simpleshop.service.OrderService;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private SellerService sellerService;

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    //TODO
    @Override
    public Order cancel(Order order) {
        return null;
    }

    @Override
    public Order delete(Long id) {
        Order order = getById(id);
        orderRepository.delete(order);
        return order;
    }

    @Override
    public Collection<OrderLine> getOrderLinesByOrderId(Long id) {
        return orderLineRepository.findAllByOrderId(id);
    }

    @Override
    public boolean canMakeOrder(Collection<CartItem> cartItems, Collection<CartItem> refuse) {
        int startSize = cartItems.size();
        for (CartItem cartItem : cartItems)
            if (cartItem.getProduct().getQuantity() < cartItem.getQuantity()) {
                refuse.add(cartItem);
                cartItems.remove(cartItem);
            } else if (!cartItem.getProduct().isEnabled()) {
                refuse.add(cartItem);
                cartItems.remove(cartItem);
            }
        return cartItems.size() == startSize;
    }

    @Override
    public Order prepareOrder(Collection<CartItem> cartItems, Buyer buyer) {
        if (buyer.getBillingAddress().isValid() && buyer.getShippingAddress().isValid()) {
            List<OrderLine> orderLines = new ArrayList<>();
            for (CartItem cartItem : cartItems)
                orderLines.add(new OrderLine(cartItem));
            sellerService.notifySellers(orderLines);
            BillingInfo billingInfo = new BillingInfo();
            billingInfo.setBillingAddress(buyer.getBillingAddress());
            Order order = new Order(orderLines, billingInfo, buyer.getShippingAddress());
            orderRepository.save(order);
            return order;
        }
        throw new IllegalCustomerStateException("Invalid customer information");
    }
}
