package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    List<Buyer> findAllByIsActiveFalse();
    Optional<Buyer> findByUserId(Long id);
}
