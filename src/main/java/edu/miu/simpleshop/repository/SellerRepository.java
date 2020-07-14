package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    List<Seller> findAllByIsActiveFalse();
    Optional<Seller> findByUserId(Long id);
}