package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    List<Buyer> findAllByIsActiveFalse();
}
