package com.example.taskmanager.repository

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*
import java.util.function.Supplier

@SpringBootTest
class TagRepositoryTest @Autowired constructor(
    val tagRepository: TagRepository
) {
    private final val tag = Tag.Builder().header("Tag")
        .build()

    val tasks = mutableListOf(
        Task.Builder().name("Test").description("test").date(Date.from(Instant.now())).tag(tag).build(),
        Task.Builder().name("Test2").description("test2").date(Date.from(Instant.now())).tag(tag).build(),
        Task.Builder().name("Test3").description("test3").date(Date.from(Instant.now())).tag(tag).build()
    )


    @AfterEach
    fun destroyAll() = tagRepository.deleteAll()

    @Test
    fun getAllTasks() {
        tag.tasks = tasks

        tagRepository.save(tag)

        val findById = tagRepository.findById(tag.id!!)
        val optionalTag = findById.orElseGet { Tag.Builder().build() }

        Assertions.assertEquals(3, optionalTag.tasks.count())
    }

    @Test
    fun insert() {
        val tag = Tag.Builder().header("Tag").build()
        val save = tagRepository.save(tag)

        assertThat(save).isEqualTo(tag)
    }

    @Test
    fun update() {
        val tag = Tag.Builder().header("Tag").build()

        tagRepository.save(tag)

        tag.header = "#Tag2Be"
        tag.tasks = mutableListOf(
            Task.Builder().name("Test").description("test").date(Date.from(Instant.now())).build(),
            Task.Builder().name("Test2").description("test2").date(Date.from(Instant.now())).build()
        )

        val save = tagRepository.save(tag)
        assertThat(save).isEqualTo(tag)
    }

    @Test
    fun cascadeDelete() {
        tag.tasks = tasks

        tagRepository.save(tag)

        tagRepository.deleteById(tag.id!!)

        Assertions.assertEquals(0, tagRepository.count())
    }
}