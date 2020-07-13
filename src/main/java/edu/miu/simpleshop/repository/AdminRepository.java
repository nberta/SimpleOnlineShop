package edu.miu.simpleshop.repository;

import edu.miu.simpleshop.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
