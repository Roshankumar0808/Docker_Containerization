package com.springBoot.docker.product_serivce.Controller;

import com.springBoot.docker.product_serivce.Entity.Product;
import com.springBoot.docker.product_serivce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello From API side";
    }

    @PostMapping("/create")
    public  String createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return "Product has been created";

     }

     @GetMapping("/items")
     public List<Product> getAllproducts(){
        return productService.getAllproducts();
     }

}
