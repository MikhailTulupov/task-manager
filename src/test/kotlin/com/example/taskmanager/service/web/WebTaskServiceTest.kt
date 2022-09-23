package com.example.taskmanager.service.web

import com.example.taskmanager.model.Task
import com.example.taskmanager.model.web.WebTag
import com.example.taskmanager.model.web.WebTask
import com.example.taskmanager.repository.TaskRepository
import com.example.taskmanager.service.TaskService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
class WebTaskServiceTest @Autowired constructor(
    val repository: TaskRepository,
    val webTaskService: WebTaskService,
    val webTagService: WebTagService,
    val taskService: TaskService
) {

    @AfterEach
    fun destroyAll() = repository.deleteAll()

    @Test
    fun save() {
        val task = webTask()

        val save = webTaskService.save(task)

        Assertions.assertEquals(task.name, save.name)
    }

    @Test
    fun update() {
        val task = webTask()

        val save = webTaskService.save(task)
        val tag = WebTag.Builder()
            .header("TAG")
            .build()

        val tag1 = webTagService.save(tag)


        save.tag = tag1.id

        val save1 = webTaskService.save(save)
        assertThat(save1).isEqualTo(save)
    }

    @Test
    fun getAll() {
        val tasks = mutableListOf(
            Task.Builder().name("Test").description("test").date(Date.from(Instant.now())).build(),
            Task.Builder().name("Test2").description("test2").date(Date.from(Instant.now())).build(),
            Task.Builder().name("Test3").description("test3").date(Date.from(Instant.now())).build()
        )

        for (task in tasks) {
            taskService.add(task)
        }

        val webTasks = webTaskService.getAll()

        Assertions.assertEquals(3, webTasks.count())
    }

    @Test
    fun delete() {
        val task = webTask()

        val save = webTaskService.save(task)
        webTaskService.delete(save.id!!)
        Assertions.assertEquals(0, repository.count())

    }

    private fun webTask() = WebTask.Builder()
        .name("Test")
        .description("test")
        .date("2022-02-22")
        .build()
}