package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findBuyerByUserId(Long id);
    Buyer findBuyerByUserUsername(String username);
}
