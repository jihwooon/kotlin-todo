package com.example.demo.service

import com.example.demo.domain.Product
import java.util.*

interface ProductService {

    fun getProduct(id: Long): Optional<Product>
}
