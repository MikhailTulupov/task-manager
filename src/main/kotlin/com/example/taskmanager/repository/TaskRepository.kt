package com.example.taskmanager.repository

import com.example.taskmanager.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Interface for generic CRUD operations on a repository for [Task] class.
 */
@Repository
interface TaskRepository : CrudRepository<Task, UUID> {}