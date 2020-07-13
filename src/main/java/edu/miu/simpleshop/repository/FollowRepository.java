package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Collection<Follow> findAllByBuyerId(Long id);
}
