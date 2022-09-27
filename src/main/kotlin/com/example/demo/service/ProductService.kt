package com.example.demo.service

import com.example.demo.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository : ProductRepository
) {
    fun getProduct(id: Long) = productRepository.findById(id)
}
