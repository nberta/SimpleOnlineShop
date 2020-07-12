package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Seller;

public interface SellerService {

    Seller save(Seller seller);
    Seller delete(Long id);
    Seller getById(Long id);


}
