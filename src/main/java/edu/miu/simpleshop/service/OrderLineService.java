package edu.miu.simpleshop.service;

import edu.miu.simpleshop.domain.OrderLine;

public interface OrderLineService {

    OrderLine getById(Long id);
    OrderLine save(OrderLine orderLine);
    OrderLine cancel(OrderLine orderLine);
}
