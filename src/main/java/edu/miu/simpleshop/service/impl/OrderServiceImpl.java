package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.repository.OrderRepository;
import edu.miu.simpleshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    //TODO
    @Override
    public Order cancel(Order order) {
        return null;
    }

    @Override
    public Order delete(Long id) {
        Order order = getById(id);
        repository.delete(order);
        return order;
    }

    @Override
    public List<OrderLine> getOrderLinesByOrderId(Long id) {
        return null;
    }
}
