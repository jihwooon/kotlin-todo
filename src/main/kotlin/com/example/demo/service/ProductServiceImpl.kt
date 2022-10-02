package com.example.demo.service

import com.example.demo.controller.ProductRequest
import com.example.demo.controller.ProductUpdateRequest
import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun getProducts(): List<Product> = productRepository.findAll() as List<Product>;

    override fun getProduct(id: Long): Optional<Product> = productRepository.findById(id)

    override fun createProduct(productRequest: ProductRequest): Product {
        val product =
            Product(name = productRequest.name, email = productRequest.email, phoneNumber = productRequest.phoneNumber)

        return productRepository.save(product)
    }

    override fun updateProduct(id: Long, productUpdateRequest: ProductUpdateRequest): Product {

        val findById = productRepository.findById(id)

        val product = Product(
            id = findById.get().id,
            name = productUpdateRequest.name,
            email = productUpdateRequest.email,
            phoneNumber = productUpdateRequest.phoneNumber
        )
        return productRepository.save(product)
    }

    override fun deleteProduct(id: Long): Unit {

        return productRepository.deleteById(id)
    }

}
