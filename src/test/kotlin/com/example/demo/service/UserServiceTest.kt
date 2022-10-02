package com.example.demo.service

import com.example.demo.ProductNotFoundException
import com.example.demo.UserNotFoundException
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
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

        given(userRepository.findById(1L)).willReturn(Optional.of(user))
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

    //TODO : UserService 조회 테스트를 구현하라
    @Test
    fun getUserId() {
        val user = userService.getUser(1L)

        assertThat(user).isNotNull

        assertThat(user.get().name).isEqualTo("abc")
    }

//    @Test
//    fun getUserNotExistedId() {
//        val user = userService.getUser(1004L).orElseThrow {
//            UserNotFoundException()
//        }
//        assertThat(user.id).isEqualTo(1004L)
//    }
}
