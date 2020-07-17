package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
