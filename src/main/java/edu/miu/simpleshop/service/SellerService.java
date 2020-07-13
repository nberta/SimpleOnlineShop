package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;

import java.util.List;

public interface SellerService {

    Seller save(Seller seller);
    Seller delete(Long id);
    Seller getById(Long id);
    List<Seller> getPendingSellers();

}
