package com.example.taskmanager.mapper

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.web.WebTag
import com.example.taskmanager.model.web.WebTask
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class TagMapperTest {

    @Test
    fun tagToWebTag() {
        val tag = Tag.Builder()
            .id(UUID.randomUUID())
            .header("Tag")
            .build()

        val tasks = mutableListOf(
            Task(
                id = UUID.randomUUID(), name = "Test1", description = "test1",
                date = Date.from(Instant.now()), tag = tag
            ),
            Task(
                id = UUID.randomUUID(), name = "Test2", description = "test2",
                date = Date.from(Instant.now()), tag = tag
            ),
            Task(
                id = UUID.randomUUID(), name = "Test3", description = "test3",
                date = Date.from(Instant.now()), tag = tag
            )
        )

        tag.tasks = tasks

        val webTag = tag.tagToWebTag()
        Assertions.assertEquals(tag.id.toString(), webTag.id)
    }

    @Test
    fun webTagToTag() {
        val webTag = WebTag.Builder()
            .id("31142668-be1a-4e2a-bc1a-d47d8c9e72dc")
            .header("Tag")
            .build()

        val webTasks = mutableListOf(
            WebTask(
                id = UUID.randomUUID().toString(), name = "Test1", description = "test1",
                date = "2022-02-02", tag = webTag.id!!
            ),
            WebTask(
                id = UUID.randomUUID().toString(), name = "Test2", description = "test2",
                date = "2022-02-02", tag = webTag.id!!
            ),
            WebTask(
                id = UUID.randomUUID().toString(), name = "Test3", description = "test3",
                date = "2022-02-02", tag = webTag.id!!
            )
        )

        webTag.tasks = webTasks

        val tag = webTag.webTagToTag()

        Assertions.assertEquals(webTag.id, tag.id.toString())
    }
}