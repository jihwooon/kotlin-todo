package com.example.demo.controller

import com.example.demo.UserNotFoundException
import com.example.demo.domain.User
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(
    private val userService: UserService
) {

    //TODO : User 전체 정보 조회를 만들어라
    @GetMapping("/users")
    fun getUserList(): List<User> {

        return userService.getList()
    }

    //TODO : User 정보 조회를 만들어라
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User {

        return userService.getUser(id).orElseThrow { UserNotFoundException()}
    }

    // TODO : User 저장을 만들어라
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userRequest: UserRequestDto): User {

        return userService.createUser(userRequest)
    }

    // TODO : User 정보를 수정하라
    @PatchMapping("/user/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updateRequest: UserUpdateDto): User {

        return userService.updateUser(id, updateRequest)
    }

    // TODO : User 정보를 삭제하라
}

data class UserRequestDto(
    val id: Long = 0,
    val name: String = "",
    val password: String = "",
    val email: String = ""

)

data class UserUpdateDto(
    val name: String = "",
    val password: String = "",
    val email: String = ""

)
