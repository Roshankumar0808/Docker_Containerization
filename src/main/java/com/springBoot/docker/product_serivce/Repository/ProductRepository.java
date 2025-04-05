package com.springBoot.docker.product_serivce.Repository;

import com.springBoot.docker.product_serivce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
