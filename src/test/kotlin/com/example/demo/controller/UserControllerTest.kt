package com.example.demo.controller

import com.example.demo.UserNotFoundException
import com.example.demo.domain.User
import com.example.demo.service.UserService
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets
import java.util.*

@WebMvcTest(UserController::class)
internal class UserControllerTest() {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `Get user return list`() {

        given(userService.getList()).willReturn(listOf())

        mvc.perform(get("/users"))
            .andExpect(status().isOk)

        verify(userService).getList()
    }

    @Test
    fun `Get user return id`() {
        val id: Long = 1L

        val user = User(id = id, name = "jihwooon", email = "abc@gmail.com", password = "1234")

        given(userService.getUser(id)).willReturn(Optional.of(user))

        mvc.perform(
            get("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)
            .andExpect(content().string("{\"id\":1,\"name\":\"jihwooon\",\"password\":\"1234\",\"email\":\"abc@gmail.com\"}"))
            .andExpect(content().string(containsString("jihwooon")))

        verify(userService).getUser(id)
    }

    @Test
    fun `Get NotFound response Incorrect_id`() {
        val id: Long = 1000L

        given(userService.getUser(id)).willThrow(UserNotFoundException())

        mvc.perform(
            get("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)

        verify(userService).getUser(id)
    }

}