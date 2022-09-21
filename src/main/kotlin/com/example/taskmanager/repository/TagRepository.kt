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
}
