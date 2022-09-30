package com.example.demo.service

import com.example.demo.controller.ProductRequest
import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun getProduct(id: Long) = productRepository.findById(id);

    override fun createProduct(productRequest: ProductRequest): Product {

        val product = Product(name = productRequest.name, email = productRequest.email, phoneNumber = productRequest.phoneNumber)

        return productRepository.save(product)
    }
}
