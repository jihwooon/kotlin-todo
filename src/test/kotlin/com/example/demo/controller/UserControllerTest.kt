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
internal class UserControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `Get user return list`() {
        given(userService.getList()).willReturn(listOf(User(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234")))

        mvc.perform(get("/users"))
            .andExpect(status().isOk)

        verify(userService).getList()
    }

    @Test
    fun `Get user return id`() {
        val id = 1L

        given(userService.getUser(id)).willReturn(Optional.of(User(id = id, name = "abc", email = "abc@gmail.com", password = "1234")))

        mvc.perform(
            get("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
        )
            .andExpect(status().isOk)
            .andExpect(content().string("{\"id\":1,\"name\":\"abc\",\"password\":\"1234\",\"email\":\"abc@gmail.com\"}"))
            .andExpect(content().string(containsString("abc")))

        verify(userService).getUser(id)
    }

    @Test
    fun `Get NotFound response Incorrect_id`() {
        val id = 1004L

        given(userService.getUser(id)).willThrow(UserNotFoundException())

        mvc.perform(
            get("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)

        verify(userService).getUser(id)
    }

    @Test
    fun `Post product response requestDto`() {
        val userRequestDto = UserRequestDto(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234")
        val content = "{\"id\":1,\"name\":\"abc\",\"password\":\"1234\",\"email\":\"abc@gmail.com\"}"
        given(userService.createUser(userRequestDto)).willReturn(User(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234"))

        mvc.perform(
            post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
            .andExpect(status().isCreated)
            .andExpect(content().string(content))

        verify(userService).createUser(userRequestDto)
    }

    @Test
    fun  `Patch product response requestUpdateDto`() {
        val userUpdateRequest = UserUpdateDto(name = "efg", password = "5678", email = "efg@gmail.com")
        val id = 1L
        val content = "{\"id\":1,\"name\":\"efg\",\"password\":\"5678\",\"email\":\"efg@gmail.com\"}"

        given(userService.updateUser(id, userUpdateRequest))
            .will { invocation ->
                val id: Long = invocation.getArgument(0)
                val userData: UserUpdateDto = invocation.getArgument(1)
                User(
                    id = id,
                    name = userData.name,
                    password = userData.password,
                    email = userData.email
                )
            }

        mvc.perform(
            patch("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
            .andExpect(status().isOk)
            .andExpect(content().string(content))

        verify(userService).updateUser(id, userUpdateRequest)
    }

    @Test
    fun  `Patch NotFoundException response requestUpdateDto`() {
        val userUpdateRequest = UserUpdateDto(name = "efg", password = "5678", email = "efg@gmail.com")
        val id = 1004L
        val content = "{\"id\":1,\"name\":\"efg\",\"password\":\"5678\",\"email\":\"efg@gmail.com\"}"

        given(userService.updateUser(id, userUpdateRequest)).willThrow(UserNotFoundException())

        mvc.perform(
            patch("/user/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
            .andExpect(status().isNotFound)

        verify(userService).updateUser(id, userUpdateRequest)
    }

}
