package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<BillingInfo, Long> {
}
