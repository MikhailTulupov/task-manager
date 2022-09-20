package com.example.taskmanager.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class TaskTest {
    @Test
    fun createFromConstructor() {
        val task = Task(
            UUID.randomUUID(), "Test",
            "test", Date.from(Instant.now()), Tag.Builder().build()
        )
        assertEquals("Test", task.name)
    }

    @Test
    fun createFromBuilder() {
        val task = Task.Builder().id(UUID.randomUUID()).name("Test").description("test").date(Date.from(Instant.now()))
            .build()
        assertEquals("Test", task.name)
    }
}