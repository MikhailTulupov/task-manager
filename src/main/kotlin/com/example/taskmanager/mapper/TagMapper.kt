package com.example.taskmanager.mapper

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.web.WebTag
import java.util.*

/**
 * Extension function for [WebTag] class. This method parse [WebTag] and deserialization to [Tag].
 */
fun WebTag.webTagToTag() = Tag.Builder()
    .id(if (id != null) UUID.fromString(id) else UUID.randomUUID())
    .header(header)
    .tasks(tasks.webTasksToTasks())
    .build()

/**
 * Extension function for [Tag] class. This method parse [Tag] and serialization to [WebTag].
 */
fun Tag.tagToWebTag() = WebTag.Builder()
    .id(id.toString())
    .header(header)
    .tasks(tasks.tasksToWebTasks())
    .build()

