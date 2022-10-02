package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// TODO : User Unit 테스트를 하라
internal class UserTest() {

    private lateinit var user: User

    @Test
    fun `User Unit Test`() {
        user = User(id = 1004L, name = "abcd", email = "abc@gmail.com", password = "1234")

        assertThat(user.id).isEqualTo(1004L)
        assertThat(user.name).isEqualTo("abcd")
        assertThat(user.email).isEqualTo("abc@gmail.com")
        assertThat(user.password).isEqualTo("1234")
    }
}
