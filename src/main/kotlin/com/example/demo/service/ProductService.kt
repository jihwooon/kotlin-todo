package com.example.demo.service

import com.example.demo.domain.Product
import java.util.*

interface ProductService {

    fun getProducts() : List<Product>?
    fun getProduct(id: Long): Optional<Product>
    fun createProduct(name : String, email : String, phoneNumber : String): Product
    fun updateProduct(id: Long, email : String, phoneNumber: String, name: String): Product?
}
