package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productServiceImpl: ProductServiceImpl) {

    @GetMapping("/product/{id}")
    fun getProduct(@PathVariable("id") id: Long): Product = productServiceImpl.getProduct(id)
        .orElseThrow()
}
