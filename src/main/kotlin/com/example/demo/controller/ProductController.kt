package com.example.demo.controller

import com.example.demo.ProductNotFoundException
import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@RestController
class ProductController(private val productServiceImpl: ProductServiceImpl) {

    @GetMapping("/products")
    fun getProducts(): List<Product>? {

        return productServiceImpl.getProducts()
    }

    @GetMapping("/product/{id}")
    fun getProduct(@PathVariable("id") id: Long): Product = productServiceImpl.getProduct(id)
        .orElseThrow{ProductNotFoundException()}

    @PostMapping("/product")
    fun createProduct(@RequestBody @Valid productRequest: ProductRequest): ResponseEntity<Any>? {

        productServiceImpl.createProduct(productRequest)
        return ResponseEntity.noContent().build()
    }


    @PatchMapping("/product/{id}")
    fun updateProduct(@PathVariable("id") id: Long, @RequestBody request: ProductUpdateRequest) : Product? {

        return productServiceImpl.updateProduct(id, request)
    }

}

data class ProductRequest(
    val name: String = "",

    @field: Email(message = "이메일 형식이 아닙니다.")
    val email: String = "",

    @field: Pattern(regexp = "^\\d{3}\\d{3,4}\\d{4}$", message = "000-0000-0000의 형식은 맞지 않습니다.")
    val phoneNumber: String = ""
)

data class ProductUpdateRequest(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = ""
)
