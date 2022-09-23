package com.example.taskmanager.service

import com.example.taskmanager.model.Task
import java.util.UUID

/**
 * Interface for management business logic [Task] entity.
 */
interface TaskService {

    /**
     * Return all entities
     *
     * @return all saves entities.
     */
    fun getAll(): List<Task>

    /**
     * Add entity.
     *
     * @param task must be non-null.
     * @return the added entity.
     */
    fun add(task: Task): Task

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     */
    fun deleteById(id: UUID)
}