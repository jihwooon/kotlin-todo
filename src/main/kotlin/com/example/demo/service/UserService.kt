package com.example.demo.service

import com.example.demo.UserNotFoundException
import com.example.demo.controller.UserRequestDto
import com.example.demo.controller.UserUpdateDto
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getList(): MutableIterable<User> {

        return userRepository.findAll()
    }

    fun getUser(id: Long) = userRepository.findById(id)

    fun createUser(userRequest: UserRequestDto) : User {

        val user = User(
            id = userRequest.id,
            name = userRequest.name,
            email = userRequest.email,
            password = userRequest.password
        )

        return userRepository.save(user)
    }

    fun updateUser(id: Long, updateRequest: UserUpdateDto): User {
        val userId = userRepository.findById(id)
            .orElseThrow { UserNotFoundException() }

        return User(
            id = userId.id,
            name = updateRequest.name,
            email = updateRequest.email,
            password = updateRequest.password
        )
    }
}
