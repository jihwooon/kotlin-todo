package com.example.demo.service

import com.example.demo.domain.ProductRepository
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.MockitoAnnotations


internal class ProductServiceImplTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var productService: ProductServiceImpl


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        productService = ProductServiceImpl(productRepository)
    }

}
