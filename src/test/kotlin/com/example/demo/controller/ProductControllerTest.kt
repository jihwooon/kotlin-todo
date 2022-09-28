package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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

    @Test
    fun `Get return id`() {
        val id = 1L

        val product = Product(name = "jihwooon")
        given(productServiceImpl.getProduct(id)).willReturn(Optional.of(product))

        val result = mock.perform(
            get("/product/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )

        result
            .andExpect(status().isOk)
            .andDo(document("get-return"))
            .andExpect(content().string(containsString("jihwooon")))
    }
}
