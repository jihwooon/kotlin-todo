package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductTest {

    @Test
    fun `Get Id`() {
        val product = Product(id = 1L)

        assertThat(product.id).isEqualTo(1L);
    }

    @Test
    fun `Get name`() {
        val product = Product(name = "jihwooon")

        assertThat(product.name).isEqualTo("jihwooon")
    }
}
