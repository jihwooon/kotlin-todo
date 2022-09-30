package com.example.demo.controller

import com.example.demo.ProductNotFoundException
import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets
import java.util.*


@WebMvcTest(ProductController::class)
@AutoConfigureRestDocs
internal class ProductControllerTest {

    @Autowired
    private lateinit var mock: MockMvc

    @MockBean
    private lateinit var productServiceImpl: ProductServiceImpl

    private lateinit var product: Product

    @BeforeEach
    fun setUp() {
        product = Product(name = "jihwooon", phoneNumber = "000000000", email = "abc@gmail.com")

        given(productServiceImpl.getProducts()).willReturn(listOf(product))

        given(productServiceImpl.getProduct(1L)).willReturn(Optional.of(product))

        given(productServiceImpl.getProduct(1000L)).willThrow(ProductNotFoundException())

        given(productServiceImpl.createProduct(name = "jihwooon", phoneNumber = "000000000", email = "abc@gmail.com"))
            .willReturn(product)
    }

    @Test
    fun `Get list response`() {
        mock.perform(get("/products")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("jihwooon")))
            .andExpect(content().string(containsString("000000000")))
            .andExpect(content().string(containsString("abc@gmail.com")))
    }

    @Test
    fun `Get product response id`() {
        val id = 1L
        mock.perform(
            get("/product/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("jihwooon")))
    }

    // id값 예외를 만들어라
    @Test
    fun `Get NotFound response id`() {
        val id = 1000L

        mock.perform(
            get("/product/$id")
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `Post 204 no content response product`() {
        val content = "{\"name\":\"jihwooon\",\"phoneNumber\":\"000000000\",\"email\":\"abc@gmail.com\"}"

        mock.perform(
            post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(content)
        )
            .andExpect(status().isNoContent)
    }
}
