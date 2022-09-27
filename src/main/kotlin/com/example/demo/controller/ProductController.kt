package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/product/{id}")
    fun getProduct(
        @PathVariable("id") id: Long
    ): Product = productService.getProduct(id)
        .orElseThrow()
}
