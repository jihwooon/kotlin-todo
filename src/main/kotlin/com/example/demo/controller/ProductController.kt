package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productServiceImpl: ProductServiceImpl) {

    @GetMapping("/product/{id}")
    fun getProduct(@PathVariable("id") id: Long): Product = productServiceImpl.getProduct(id)
        .orElseThrow()

    @PostMapping("/product")
    fun createProduct(@RequestBody productRequest : ProductRequest) : ResponseEntity<Any>? {

        productServiceImpl.createProduct(productRequest)
        return ResponseEntity.noContent().build()
    }
}

class ProductRequest (
    val name : String = "",
    val email : String = "",
    val phoneNumber : String = ""
)

class ProductResponse (

)
