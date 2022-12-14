package com.example.demo.service

import com.example.demo.UserNotFoundException
import com.example.demo.controller.UserRequestDto
import com.example.demo.controller.UserUpdateDto
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
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

    }

    @Test
    fun getUsersWithNoUser() {
        given(userRepository.findAll()).willReturn(listOf())

        assertThat(userService.getList()).isEmpty()

    }

    @Test
    fun getUsersWithExistedUser() {
        val user = User(id = 1L, name = "abc", email = "abc@gmail.com", password = "1234")

        given(userRepository.findAll()).willReturn(mutableListOf(user))

        val users = userService.getList()

        assertThat(users).isNotEmpty
        assertThat(users.elementAt(0).name).isEqualTo("abc")

    }

    @Test
    fun getUserWithId() {
        val id = 1L

        given(userRepository.findById(id)).willReturn(
            Optional.of(User(id = id))
        )

        val user = userService.getUser(id)

        assertThat(user.get().id).isEqualTo(id)

        verify(userRepository).findById(id)
    }

    @Test
    fun `User NotFound response id`() {

        given(userRepository.findById(1004L)).willReturn(Optional.empty())

        assertThatThrownBy{
            userService.getUser(1004L).orElseThrow { UserNotFoundException()}
        }.isInstanceOf(UserNotFoundException::class.java)
    }

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

        val saved = userService.createUser(userRequest)

        assertThat(saved.id).isEqualTo(1L)

    }

    @Test
    @Suppress("Name_Shadowed")
    fun updateUserWithExistedId() {
//        val id = 1L
        val userUpdateRequest = UserUpdateDto(name = "efg", password = "5678", email = "efg@gmail.com")

        given(userRepository.findById(1L)).willReturn(
            Optional.of(User(id = 1L))
        )

        given(userRepository.save(any()))
            .will { invocation ->
                val id: Long = invocation.getArgument(0)
                val userData: UserUpdateDto = invocation.getArgument(1)
                User(
                    id = id,
                    name = userData.name,
                    email = userData.email,
                    password = userData.password
                )
            }

        val updateUser = userService.updateUser(1L, userUpdateRequest)

        assertThat(updateUser.id).isEqualTo(1L)
        assertThat(updateUser.name).isEqualTo("efg")
        assertThat(updateUser.password).isEqualTo("5678")
        assertThat(updateUser.email).isEqualTo("efg@gmail.com")

    }

    // TODO : ??? ????????? ??? ????????? ?????? ??????
//    @Test
//    fun updateUserWithNotExistedId() {
//        val id = 1004L
//        val userUpdateRequest = UserUpdateDto(name = "efg", password = "5678", email = "efg@gmail.com")
//
//        given(userRepository.findById(id)).willReturn(
//            Optional.of(User(id = id))
//        )
//        val user = userService.getUser(id).orElseThrow {
//            UserNotFoundException()
//        }
//        assertThatThrownBy { userService.updateUser(user.id, userUpdateRequest) }
//            .isInstanceOf(ProductNotFoundException::class.java)
//
//    }
}
