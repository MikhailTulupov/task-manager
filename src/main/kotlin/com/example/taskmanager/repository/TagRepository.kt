package com.example.taskmanager.repository

import com.example.taskmanager.model.Tag
import com.example.taskmanager.model.Task
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * /**
 * Interface for generic CRUD operations on a repository for [Tag] class.
*/
 */
@Repository
interface TagRepository : CrudRepository<Tag, UUID> {
    /**
     * Return all instances type [Task], witch refer to specific tag.
     * @param id must be non-null
     * @return all entities witch refer to specific tag
     */
    @Query("SELECT t.tasks FROM Tag t WHERE t.id = :tag_id")
    fun findAllTaskByTagId(@Param("tag_id") id: UUID): List<Task>
}