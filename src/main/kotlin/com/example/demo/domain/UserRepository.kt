package com.example.demo.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
}
