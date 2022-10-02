package com.example.demo.controller

import com.example.demo.domain.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(

    private val userService : UserService
) {

    //TODO : User 전체 정보 조회를 만들어라
    @GetMapping("/users")
    fun getUserList() : List<User> {

        return userService.getList()
    }

    //TODO : User 정보 조회를 만들어라
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id : Long) : Optional<User> {

        return userService.getUser(id)
    }

    // TODO : User 정보를 수정하라


    // TODO : User 정보를 삭제하라
}
