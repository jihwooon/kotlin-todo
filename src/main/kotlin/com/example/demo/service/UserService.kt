package com.example.demo.service

import com.example.demo.domain.User
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService {
    fun getList(): List<User>? {

        return null
    }

    fun getUser(id: Long): Optional<User>? {
        return null
    }
}
