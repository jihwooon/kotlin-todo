package com.example.demo.controller

import com.example.demo.ProductNotFoundException
import com.example.demo.domain.Product
import com.example.demo.service.ProductServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
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

    private var notProductId = 1000L

    private var productId = 1L

    @BeforeEach
    fun setUp() {

        product = Product()

        given(productServiceImpl.getProducts()).willReturn(mutableListOf(product))

        given(productServiceImpl.getProduct(productId)).willReturn(Optional.of(product))

        given(productServiceImpl.getProduct(notProductId)).willReturn(Optional.empty())

        given(productServiceImpl.getProduct(notProductId)).willThrow(ProductNotFoundException())

    }

    @Test
    fun `Get list response`() {
        mock.perform(
            get("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)

        verify(productServiceImpl).getProducts()
    }

    @Test
    fun `Get response true`() {
        mock.perform(
            get("/product/$productId")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)
            .andExpect(content().string("true"))
    }

    @Test
    fun `Get NotFound response false`() {
        mock.perform(
            get("/product/$notProductId")
        )
            .andExpect(status().isNotFound)
            .andExpect(content().string("{}"))
    }

//    @Test
//    fun `Post 204 no content response product`() {
//        val content = "{\"season_id\":\1"\",\"phoneNumber\":\"01012345678\",\"email\":\"abc@codesoom.com\"}"
//
//        mock.perform(
//            post("/product")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(StandardCharsets.UTF_8.name())
//                .content(content)
//        )
//            .andExpect(status().isNoContent)
//    }
// NotificationsController 테스트 코드를 구현하라

// NotificationsController에 테스트 코드를 작성했습니다.
//    //TODO : update를 만들어라
//    @Test
//    fun `Update product reponse product`() {
//        val content = "{\"name\":\"jihwooon\",\"phoneNumber\":\"010-1111-3333\",\"email\":\"abc@gmail.com\"}"
//        val id = 1L
//
//        mock.perform(
//            patch("/product/$id")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(StandardCharsets.UTF_8.name())
//                .content(content)
//        )
//            .andExpect(status().isOk)
//            .andExpect(content().string(containsString("jihwooon")))
//
//    }
}
