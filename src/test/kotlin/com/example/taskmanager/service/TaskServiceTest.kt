package com.example.taskmanager.service

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TaskRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
class TaskServiceTest @Autowired constructor(
    val service: TaskService,
    val taskRepository: TaskRepository
) {
    private val tag = Tag.Builder().header("Test").build()
    private val tasks = mutableListOf(
        Task.Builder().name("Test").description("test").date(Date.from(Instant.now())).tag(tag).build(),
        Task.Builder().name("Test2").description("test2").date(Date.from(Instant.now())).tag(tag).build(),
        Task.Builder().name("Test3").description("test3").date(Date.from(Instant.now())).tag(tag).build()
    )

    init {
        tag.tasks = tasks
    }

    @AfterEach
    fun destroyAll() {
        taskRepository.deleteAll()
    }

    @Test
    fun getAll() {
        for (task in tasks) {
            service.add(task)
        }

        Assertions.assertEquals(3, service.getAll().count())
    }

    @Test
    fun add() {
        val add = service.add(tasks[0])

        assertThat(add).isEqualTo(tasks[0])
    }

    @Test
    fun update() {
        service.add(tasks[0])

        tasks[0].name = "TestTest"

        val update = service.add(tasks[0])

        assertThat(update).isEqualTo(tasks[0])
    }

    @Test
    fun deleteById() {
        service.add(tasks[0])
        service.deleteById(tasks[0].id!!)

        Assertions.assertEquals(2, taskRepository.count())
    }
}