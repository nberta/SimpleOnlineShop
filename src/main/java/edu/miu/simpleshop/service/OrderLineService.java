package edu.miu.simpleshop.service;

import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Seller;

import java.util.Collection;

public interface OrderLineService {

    OrderLine getById(Long id);
    OrderLine save(OrderLine orderLine);
    OrderLine updateToShipped(Long id, Seller seller);
    Collection<OrderLine> findAllByOrderId(Long id);
}
