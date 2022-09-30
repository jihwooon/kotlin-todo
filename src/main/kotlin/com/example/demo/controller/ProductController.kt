package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class ProductController(private val productServiceImpl: ProductServiceImpl) {

    @GetMapping("/products")
    fun getProducts(): List<Product>? {

        return productServiceImpl.getProducts()
    }

    @GetMapping("/product/{id}")
    fun getProduct(@PathVariable("id") id: Long): Product = productServiceImpl.getProduct(id)
        .orElseThrow()

    @PostMapping("/product")
    fun createProduct(@RequestBody @Valid productRequest: ProductRequest): ResponseEntity<Any>? {

        productServiceImpl.createProduct(productRequest.name, productRequest.email, productRequest.phoneNumber)
        return ResponseEntity.noContent().build()
    }
}

data class ProductRequest(
    val name: String = "",

    @field: NotBlank(message = "이메일은 필수 입니다.")
    val email: String = "",
    val phoneNumber: String = ""
)
