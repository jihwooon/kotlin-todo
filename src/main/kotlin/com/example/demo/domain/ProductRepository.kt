package com.example.demo.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductRepository : CrudRepository<Product, Long> {

    override fun findById(id: Long): Optional<Product>

}

