package com.springBoot.docker.product_serivce.Service;

import com.springBoot.docker.product_serivce.Entity.Product;
import com.springBoot.docker.product_serivce.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
        log.info("Product created");
    }

    public List<Product> getAllproducts() {
        log.info("Fetching All Products");
        List<Product> products=productRepository.findAll();
        return products;
    }
}
