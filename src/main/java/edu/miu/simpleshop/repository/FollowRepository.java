package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Follow;
import edu.miu.simpleshop.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowRepository extends CrudRepository <Follow, Long> {


        public List<Seller> findAllBySeller(Long id);

        public List<Seller> findAllByProduct(Long id);

        public Follow findFollowByBuyerAndSeller(Long sellerId, Long buyerId);




    }

