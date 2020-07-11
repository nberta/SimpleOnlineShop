package edu.miu.simpleshop.repository;



import edu.miu.simpleshop.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long>{

}
