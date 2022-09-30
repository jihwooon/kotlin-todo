package com.example.demo.service

import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductServiceImplTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var productServiceImpl: ProductServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        productServiceImpl = ProductServiceImpl(
            productRepository
        )
    }

    @Test
    fun getProduct() {
        val name = "jihwoon"
        val id = 1L
        val mockProduct = Product(name = name)

        given(productRepository.findById(id)).willReturn(Optional.of(mockProduct));

        val product = productServiceImpl.getProduct(id)
        assertThat(product.get().name).isEqualTo(name)
    }

    @Test
    fun createProduct() {
        val name = "jihwooon"
        val email = "abc@gmail.com"
        val phoneNumber = "0000000"

        given(productRepository.save(any())).willReturn(
            Product(
                name = "jihwooon",
                email = "abc@gmail.com",
                phoneNumber = "0000000"
            )
        )

        val product = productServiceImpl.createProduct(name, email, phoneNumber)

        assertThat(product.name).isEqualTo("jihwooon")
        assertThat(product.email).isEqualTo("abc@gmail.com")
        assertThat(product.phoneNumber).isEqualTo("0000000")

    }
}
