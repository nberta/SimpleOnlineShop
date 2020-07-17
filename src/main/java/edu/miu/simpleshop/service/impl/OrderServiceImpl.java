package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.domain.enums.OrderStatus;
import edu.miu.simpleshop.exception.IllegalCustomerStateException;
import edu.miu.simpleshop.repository.OrderLineRepository;
import edu.miu.simpleshop.repository.OrderRepository;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.OrderService;
import edu.miu.simpleshop.service.SellerService;
import edu.miu.simpleshop.util.ReceiptMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BuyerService buyerService;

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

    @Override
    public Order cancel(Long id) {
        Order order = getById(id);
        for (OrderLine ol : order.getOrderLines()) {
            if (!ol.getStatus().equals(OrderStatus.SHIPPED) || !ol.getStatus().equals(OrderStatus.DELIVERED))
                ol.setStatus(OrderStatus.CANCELLED);
        }
        save(order);
        return order;
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
        Collection<CartItem> unavailableItems = cartItems.stream()
                .filter(cartItem ->
                        cartItem.getProduct().getQuantity() < cartItem.getQuantity() ||
                        !cartItem.getProduct().isEnabled())
                .collect(Collectors.toList());
        return unavailableItems.isEmpty();
    }

    @Override
    public Order prepareOrder(Collection<CartItem> cartItems, Buyer buyer) {
        if (buyer.getBillingAddress().isValid() && buyer.getShippingAddress().isValid()) {
            List<OrderLine> orderLines = cartItems.stream()
                    .map(OrderLine::new)
                    .collect(Collectors.toList());

            sellerService.notifySellers(orderLines);

            BillingInfo billingInfo = new BillingInfo();
            billingInfo.setBillingAddress(buyer.getBillingAddress());

            Order order = new Order(orderLines, billingInfo, buyer.getShippingAddress());
            orderRepository.save(order);
            ReceiptMaker.prepareOrderReceipt(order);

            buyer.setGainPoints(buyer.getGainPoints() + (int)Math.floor(order.getTotalCost()/10));
            buyerService.save(buyer);

            return order;
        }
        throw new IllegalCustomerStateException("Invalid customer information. Please set billing and shipping address");
    }
}
