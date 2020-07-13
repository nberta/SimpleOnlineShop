package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Seller;

import java.util.List;

public interface SellerService {

    Seller save(Seller seller);
    Seller delete(Long id);
    Seller getById(Long id);
    void notifySellers(List<OrderLine> orderLines);

}
