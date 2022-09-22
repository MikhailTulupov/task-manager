package com.example.taskmanager.service

import com.example.taskmanager.model.Tag
import java.util.UUID

/**
 * Interface for management business logic [Tag] entity.
 */
interface TagService {
    /**
     * Add entity.
     *
     * @param tag must be non-null.
     * @return the added entity.
     */
    fun add(tag: Tag): Tag

    /**
     * Update exist entity.
     *
     * @param tag must be non-null.
     * @return the updated entity.
     */
    fun update(tag: Tag): Tag

    /**
     * Retrieves an entity by its id.
     *
     * @param id must be non-null
     * @return the entity with the given id or none found.
     */
    fun findById(id: UUID): Tag

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     */
    fun deleteById(id: UUID)
}