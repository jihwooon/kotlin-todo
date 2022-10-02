package com.example.demo.service

import com.example.demo.UserNotFoundException
import com.example.demo.controller.UserRequestDto
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.refEq
import org.mockito.kotlin.verify
import java.util.*


internal class UserServiceTest {

    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userService = UserService(userRepository)

        val user = User(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234")

        given(userRepository.findAll()).willReturn(listOf(user))

    }

    @Test
    fun getUsersWithNoUser() {
        given(userRepository.findAll()).willReturn(listOf())

        assertThat(userService.getList()).isEmpty()

    }

    @Test
    fun getUsersWithExistedUser() {
        val users = userService.getList()

        assertThat(users).isNotEmpty

        assertThat(users[0].name).isEqualTo("abc")
    }

    @Test
    fun getUserId() {
        val id = 1004L

        given(userRepository.findById(id)).willReturn(
            Optional.of(User(id = id))
        )

        val user = userService.getUser(id).orElseThrow {
            UserNotFoundException()
        }

        assertThat(user.id).isEqualTo(id)
    }

//    // TODO : 왜 예외가 안 되는지 확인 하기
//    @Test
//    fun `User NotFound response id`() {
//        assertThatThrownBy{
//            userService.getUser(1004L)
//        }.isInstanceOf(
//            UserNotFoundException::class.java
//        )
//    }

    @Test
    fun createUser() {
        val userRequest = UserRequestDto(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234")

        val user = User(
            id = userRequest.id,
            name = userRequest.name,
            email = userRequest.email,
            password = userRequest.password
        )

        given(userRepository.save(any())).willReturn(user)

        val createUser = userService.createUser(userRequest)

        assertThat(createUser.id).isEqualTo(1L)

        //Object argument that is reflection-equal to the given value with support for excluding selected fields from a class.
        verify(userRepository).save(refEq(user))

    }
}
