package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(ProductController::class)
internal class ProductControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var productServiceImpl: ProductServiceImpl

    @Test
    fun `Get return id`() {
        val id = 1L

        val product = Product(name = "jihwooon")
        given(productServiceImpl.getProduct(id)).willReturn(Optional.of(product))

        mvc.perform(get("/product/$id"))
            .andExpect(status().isOk)
            .andExpect(content()
                .string(containsString("jihwooon")))
    }
}
