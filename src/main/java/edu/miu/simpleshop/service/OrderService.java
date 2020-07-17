package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.CartItem;
import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.OrderLine;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    Order getById(Long id);
    List<Order> getAllOrders();
    Order save(Order order);
    Order cancel(Long id);
    Order delete(Long id);
    Collection<OrderLine> getOrderLinesByOrderId(Long id);
    boolean canMakeOrder(Collection<CartItem> cartItems, Collection<CartItem> possibleRefuse);
    Order prepareOrder(Collection<CartItem> cartItems, Buyer buyer);
}
