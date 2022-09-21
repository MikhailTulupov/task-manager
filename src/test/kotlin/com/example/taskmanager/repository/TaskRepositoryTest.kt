package com.example.taskmanager.repository

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import java.time.Instant
import java.util.*

@SpringBootTest
class TaskRepositoryTest @Autowired constructor(
    val repository: TaskRepository
) {

    @AfterEach
    fun destroyAll() = repository.deleteAll()

    @Test
    fun getAll() {
        val listTask = mutableListOf(
            Task.Builder()
                .name("Test1")
                .description("test1")
                .date(Date.from(Instant.now()))
                .tag(Tag.Builder().header("Tag1").build()).build(),
            Task.Builder()
                .name("Test2")
                .description("test2")
                .date(Date.from(Instant.now()))
                .tag(Tag.Builder().header("Tag2").build()).build(),
            Task.Builder()
                .name("Test3")
                .description("test3")
                .date(Date.from(Instant.now()))
                .tag(Tag.Builder().header("Tag3").build()).build()
        )

        repository.saveAll(listTask)

        val foundList = repository.findAll()

        assertThat(foundList).isEqualTo(listTask)
    }

    @Test
    fun insert() {
        val task = Task.Builder()
            .name("Test")
            .description("test")
            .date(Date.from(Instant.now()))
            .tag(Tag.Builder().header("Tag").build()).build()

        val save = repository.save(task)

        assertThat(save).isEqualTo(task)
    }

    @Test
    fun update() {
        val task = Task.Builder()
            .name("Test")
            .description("test")
            .date(Date.from(Instant.now()))
            .tag(Tag.Builder().header("Tag").build()).build()

        repository.save(task)

        task.name = "CoolTest"
        task.description = "Great!"

        val save = repository.save(task)

        assertThat(save).isEqualTo(task)
    }

    @Test
    fun delete() {
        val task = Task.Builder()
            .name("Test")
            .description("test")
            .date(Date.from(Instant.now()))
            .tag(Tag.Builder().header("Tag").build()).build()

        repository.save(task)
        repository.deleteById(task.id!!)

        assertThat(repository.findByIdOrNull(task.id!!)).isNull()
    }
}