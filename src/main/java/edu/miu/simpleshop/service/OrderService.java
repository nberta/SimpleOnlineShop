package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.OrderLine;

import java.util.List;

public interface OrderService {

    Order getById(Long id);
    List<Order> getOrdersForBuyer(Long id);
    Order save(Order order);
    Order cancel(Order order);
    Order delete(Long id);
    List<OrderLine> getOrderLinesByOrderId(Long id);

}
