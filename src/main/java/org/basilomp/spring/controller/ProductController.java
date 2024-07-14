package org.basilomp.spring.controller;

import lombok.RequiredArgsConstructor;
import org.basilomp.spring.dto.ProductRequestDto;
import org.basilomp.spring.dto.ProductResponseDto;
import org.basilomp.spring.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductResponseDto getProduct(@RequestParam(name = "id") Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/user")
    public List<ProductResponseDto> getProductsByUser(@RequestParam(name = "userId") Integer userId) {
        return productService.getProductsByUserId(userId);
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDto product) {
        productService.saveProduct(product);
    }
}
