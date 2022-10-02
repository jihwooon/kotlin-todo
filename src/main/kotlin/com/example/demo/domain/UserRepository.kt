package com.example.demo.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {

    override fun findById(id: Long): Optional<User>

    fun save(user : User): User
}
