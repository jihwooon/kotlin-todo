package com.example.demo.service

import com.example.demo.domain.Product
import com.example.demo.domain.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductServiceImplTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @InjectMocks
    private lateinit var productServiceImpl: ProductServiceImpl


    @Test
    fun getProduct() {
        var name = "jihwoon"
        var id = 1L
        val mockProduct = Product(name = name)
        given(productRepository.findById(id)).willReturn(Optional.of(mockProduct));

        val product = productServiceImpl.getProduct(id)
        assertThat(product.get().name).isEqualTo(name)
    }

}
