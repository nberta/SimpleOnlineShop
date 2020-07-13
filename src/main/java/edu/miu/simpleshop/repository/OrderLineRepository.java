package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    Collection<OrderLine> findAllByProductId(Long id);
}
