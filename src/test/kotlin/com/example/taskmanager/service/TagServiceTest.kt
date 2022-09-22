package com.example.taskmanager.service

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TagRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
class TagServiceTest @Autowired constructor(
    val repository: TagRepository,
    val service: TagService
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
        repository.deleteAll()
    }

    @Test
    fun add() {
        val add = service.add(tag)

        assertThat(add).isEqualTo(tag)
    }

    @Test
    fun update() {
        service.add(tag)

        tag.header = "MyTag"

        val update = service.update(tag)

        assertThat(update).isEqualTo(tag)
    }

    @Test
    fun findById() {
        service.add(tag)

        val findById = service.findById(tag.id!!)

        assertThat(findById).isEqualTo(tag)
    }

    @Test
    fun nonFindById() {
        val findById = service.findById(UUID.randomUUID())
        assertThat(findById.id).isEqualTo(null)
    }

    @Test
    fun deleteById() {
        service.add(tag)
        service.deleteById(tag.id!!)

        Assertions.assertEquals(0, repository.count())
    }
}