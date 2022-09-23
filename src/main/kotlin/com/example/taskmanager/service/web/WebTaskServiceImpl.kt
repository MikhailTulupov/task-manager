package com.example.taskmanager.service.web

import com.example.taskmanager.mapper.taskToWebTask
import com.example.taskmanager.mapper.tasksToWebTasks
import com.example.taskmanager.mapper.webTaskToTask
import com.example.taskmanager.model.web.WebTask
import com.example.taskmanager.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class WebTaskServiceImpl @Autowired constructor(
    val taskService: TaskService
): WebTaskService {
    override fun save(webTask: WebTask): WebTask = taskService.add(webTask.webTaskToTask()).taskToWebTask()

    override fun getAll(): List<WebTask> = taskService.getAll().tasksToWebTasks()

    override fun delete(uuid: String) = taskService.deleteById(UUID.fromString(uuid))
}