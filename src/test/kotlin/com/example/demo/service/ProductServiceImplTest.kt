package com.example.demo.service

import com.example.demo.ProductNotFoundException
import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.util.*


@ExtendWith(MockitoExtension::class)
class ProductServiceImplTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var productServiceImpl: ProductServiceImpl

    private lateinit var mockProduct: Product

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        productServiceImpl = ProductServiceImpl(
            productRepository
        )

        val id = 1L
        mockProduct = Product(name = "jihwooon", email = "abc@gmail.com", phoneNumber = "0000000")

        given(productRepository.findById(id)).willReturn(Optional.of(mockProduct));

        given(productRepository.save(any())).willReturn(
            Product(
                name = "jihwooon",
                email = "abc@gmail.com",
                phoneNumber = "0000000"
            )
        )

    }

    @MockitoSettings(strictness = Strictness.WARN)
    @Test
    fun getProduct() {
        val product = productServiceImpl.getProduct(1L)

        assertThat(product.get().name).isEqualTo(mockProduct.name)
    }

//    @MockitoSettings(strictness = Strictness.WARN)
//    @Test
//    fun createProduct() {
//        val product = productServiceImpl.createProduct(mockProduct.name, mockProduct.email, mockProduct.phoneNumber)
//
//        assertThat(product.name).isEqualTo("jihwooon")
//        assertThat(product.email).isEqualTo("abc@gmail.com")
//        assertThat(product.phoneNumber).isEqualTo("0000000")
//    }
}
