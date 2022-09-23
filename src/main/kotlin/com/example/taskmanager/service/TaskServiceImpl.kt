package com.example.taskmanager.service

import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Implementation [TaskService] interface.
 */
@Service
class TaskServiceImpl @Autowired constructor(
    val repository: TaskRepository
) : TaskService {

    override fun getAll(): List<Task> = repository.findAll().toList()

    override fun add(task: Task): Task = repository.save(task)

    override fun deleteById(id: UUID) = repository.deleteById(id)
}