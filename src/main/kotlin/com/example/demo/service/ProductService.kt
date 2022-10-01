package com.example.demo.service

import com.example.demo.controller.ProductRequest
import com.example.demo.controller.ProductUpdateRequest
import com.example.demo.controller.isBooleanResponse
import com.example.demo.domain.Product

interface ProductService {

    fun getProducts(): List<Product>
    fun getProduct(id: Long): isBooleanResponse
    fun createProduct(productRequest: ProductRequest): Product
    fun updateProduct(id: Long, productUpdateRequest: ProductUpdateRequest): Product?
    fun deleteProduct(id: Long): Unit

}
