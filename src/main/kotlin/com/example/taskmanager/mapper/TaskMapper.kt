package com.example.taskmanager.mapper

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.web.WebTask
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function. This method parse [WebTask] and deserialization to [Task].
 */
fun WebTask.webTaskToTask() = Task.Builder()
    .id(if (id != null) UUID.fromString(id) else UUID.randomUUID())
    .name(name)
    .description(description)
    .date(SimpleDateFormat("yyyy-MM-dd").parse(date))
    .tag(if (tag != null) Tag.Builder().id(UUID.fromString(tag)).build() else null)
    .build()

/**
 * Extension function. This method parse [Task] and serialization to [WebTask].
 */
fun Task.taskToWebTask() = WebTask.Builder()
    .id(id.toString())
    .name(name)
    .description(description)
    .date(SimpleDateFormat("yyyy-MM-dd").format(date))
    .tag(tag?.id.toString())
    .build()

/**
 * Extension function. This method parse list of [WebTask]`s and serialization to list of [Task]`s.
 */
fun List<Task>.tasksToWebTasks(): MutableList<WebTask> {
    val webTasks = mutableListOf<WebTask>()
    for (task in this) {
        val webTask = task.taskToWebTask()

        webTasks.add(webTask)
    }
    return webTasks
}

/**
 * Extension function. This method parse list of [Task]`s and deserialization to list of [WebTask]`s.
 */
fun List<WebTask>.webTasksToTasks(): MutableList<Task> {
    val tasks = mutableListOf<Task>()
    for (webTask in this) {
        val task = webTask.webTaskToTask()

        tasks.add(task)
    }
    return tasks
}