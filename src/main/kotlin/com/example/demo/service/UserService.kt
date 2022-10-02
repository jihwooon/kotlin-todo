package com.example.demo.service

import com.example.demo.controller.UserRequestDto
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getList(): List<User> {

        return userRepository.findAll() as List<User>
    }

    fun getUser(id: Long): Optional<User> {
        return userRepository.findById(id)

    }

    fun createUser(userRequest: UserRequestDto) : User {

        val user = User(
            id = userRequest.id,
            name = userRequest.name,
            email = userRequest.email,
            password = userRequest.password
        )

        return userRepository.save(user)
    }
}
