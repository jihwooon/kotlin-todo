package com.example.demo.service

import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun getProducts(): List<Product>? = null

    override fun getProduct(id: Long) = productRepository.findById(id);

    override fun createProduct(name : String, email : String, phoneNumber : String): Product {

        val product = Product(name = name, email = email, phoneNumber = phoneNumber)

        return productRepository.save(product)
    }

    override fun updateProduct(id: Long, email: String, phoneNumber: String, name: String): Product? {
        return null
    }

}
