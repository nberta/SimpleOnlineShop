package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.repository.SellerRepository;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller delete(Long id) {
        Seller seller = getById(id);
        sellerRepository.delete(seller);
        return seller;
    }

    @Override
    public Seller getById(Long id) {
        return sellerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
