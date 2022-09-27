package com.example.demo.service

import com.example.demo.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun getProduct(id: Long) = productRepository.findById(id);
}
