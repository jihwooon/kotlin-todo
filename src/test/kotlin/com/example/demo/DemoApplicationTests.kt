package com.example.demo

import com.example.demo.domain.ProductRepository
import com.example.demo.domain.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class DemoApplicationTests {

    @MockBean
    private lateinit var productRepository: ProductRepository

    @MockBean
    private lateinit var userRepository: UserRepository

    @Test
    fun contextLoads() {
    }

}
