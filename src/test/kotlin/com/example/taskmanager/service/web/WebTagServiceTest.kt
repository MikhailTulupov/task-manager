package com.example.taskmanager.service.web

import com.example.taskmanager.model.web.WebTag
import com.example.taskmanager.model.web.WebTask
import com.example.taskmanager.repository.TagRepository
import com.example.taskmanager.repository.TaskRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebTagServiceTest @Autowired constructor(
    val tagRepository: TagRepository,
    val taskRepository: TaskRepository,
    val webTagService: WebTagService
) {
    private var webTag: WebTag = WebTag.Builder()
        .header("Tag")
        .build()

    @AfterEach
    fun destroyAll() = tagRepository.deleteAll()

    @Test
    fun save() {
        val save = webTagService.save(webTag)

        Assertions.assertEquals(webTag.header, save.header)
    }

    @Test
    fun update() {
        val save = webTagService.save(webTag)

        val webTasks = webTasks(save)

        save.tasks = webTasks

        val update = webTagService.save(save)

        assertThat(update).isEqualTo(save)
    }

    @Test
    fun getById() {
        val save = webTagService.save(webTag)

        val webTasks = webTasks(save)

        save.tasks = webTasks

        webTagService.save(save)

        val byId = webTagService.getById(save.id!!)

        assertThat(byId).isEqualTo(save)
        Assertions.assertEquals(3, byId.tasks.count())
    }

    @Test
    fun deleteById() {
        val save = webTagService.save(webTag)

        val webTasks = webTasks(save)

        save.tasks = webTasks

        webTagService.save(save)

        webTagService.deleteById(save.id!!)

        Assertions.assertEquals(0, tagRepository.count())
        Assertions.assertEquals(0, taskRepository.count())
    }

    private fun webTasks(webTag: WebTag) = mutableListOf(
        WebTask.Builder()
            .name("Test1")
            .description("test1")
            .date("2022-02-02")
            .tag(webTag.id!!)
            .build(),
        WebTask.Builder()
            .name("Test2")
            .description("test2")
            .date("2022-02-02")
            .tag(webTag.id!!)
            .build(),
        WebTask.Builder()
            .name("Test2")
            .description("test2")
            .date("2022-02-02")
            .tag(webTag.id!!)
            .build()
    )
}