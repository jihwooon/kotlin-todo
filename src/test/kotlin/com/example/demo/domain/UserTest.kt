package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("User 클래스")
internal class UserTest {

    val user = User(id = 1004L, name = "abcd", email = "abc@gmail.com", password = "1234")

    @Nested
    @DisplayName("값이 주어진다면")
    inner class DescribeOfValue {
        @Test
        fun `id 값을 리턴한다`() {
            assertThat(user.id).isEqualTo(1004L)
        }

        @Test
        fun `name 값을 리턴한다`() {
            assertThat(user.name).isEqualTo("abcd")
        }

        @Test
        fun `email 값을 리턴한다`() {
            assertThat(user.email).isEqualTo("abc@gmail.com")
        }

        @Test
        fun `password 값을 리턴한다`() {
            assertThat(user.password).isEqualTo("1234")
        }
    }
}
