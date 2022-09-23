package com.example.taskmanager.service.web

import com.example.taskmanager.model.web.WebTag

/**
 * Interface provides operations for manipulated [WebTag] data.
 */
interface WebTagService {
    /**
     * Saves entity.
     * @param webTag must be non-null
     * @return saved entity.
     */
    fun save(webTag: WebTag): WebTag

    /**
     * Retrieves an entity by its id.
     * @param uuid must be non-null
     * @return the entity with the given id or none found.
     */
    fun getById(uuid: String): WebTag

    /**
     * Deletes the entity with the given id.
     *
     * @param uuid must not be {@literal null}.
     */
    fun deleteById(uuid: String)
}