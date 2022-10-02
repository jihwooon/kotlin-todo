package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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
    }

}



