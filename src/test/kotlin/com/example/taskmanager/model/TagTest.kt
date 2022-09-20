package com.example.taskmanager.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class TagTest {
    @Test
    fun createFromConstructor() {
        val tag = Tag(id = UUID.randomUUID(), header = "Test", tasks = mutableListOf())
        assertEquals("Test", tag.header)
    }

    @Test
    fun createFromBuilder() {
        val tag = Tag.Builder().id(UUID.randomUUID()).header("Test").build()
        assertEquals("Test", tag.header)
    }
}