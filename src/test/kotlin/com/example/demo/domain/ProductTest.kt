package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductTest {

    private lateinit var product: Product

    @BeforeEach
    fun setUp() {
        product = Product(id = 1L, name = "jihwooon", email = "abc@gmail.com", phoneNumber = "000000000")
    }

    @Test
    fun `Get Id`() {
        assertThat(product.id).isEqualTo(1L);
    }

    @Test
    fun `Get name`() {
        assertThat(product.name).isEqualTo("jihwooon")
    }

    @Test
    fun `Get email`() {
        assertThat(product.email).isEqualTo("abc@gmail.com")
    }

    @Test
    fun `Get phoneNumber`() {
        assertThat(product.phoneNumber).isEqualTo("000000000")
    }
}
