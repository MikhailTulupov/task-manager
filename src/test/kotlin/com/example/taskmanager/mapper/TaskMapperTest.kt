package com.example.taskmanager.mapper

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.web.WebTask
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class TaskMapperTest {
    @Test
    fun webTaskToTask() {
        val webTask = WebTask(
            id = "bc60c2ba-3f2a-41a2-90e8-da914fb15844", name = "Test", description = "test",
            date = "2022-02-02", tag = "cf07fcf9-6393-4645-8578-3a68f03ce80f"
        )
        val task = webTask.webTaskToTask()

        Assertions.assertEquals(webTask.id, task.id.toString())
    }

    @Test
    fun taskToWebTask() {
        val tag = Tag(id = UUID.randomUUID(), header = "Tag")
        val task = Task(
            id = UUID.randomUUID(), name = "Test", description = "test",
            date = Date.from(Instant.now()), tag = tag
        )

        tag.tasks = mutableListOf(task)

        val webTask = task.taskToWebTask()
        Assertions.assertEquals(task.id, UUID.fromString(webTask.id))
    }

    @Test
    fun tasksToWebTasks() {
        val tag = Tag(id = UUID.randomUUID(), header = "Tag")
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

        val tasksToWebTasks = tasks.tasksToWebTasks()
        Assertions.assertEquals(3, tasksToWebTasks.count())
    }
}