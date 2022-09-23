package com.example.taskmanager.service.web

import com.example.taskmanager.model.web.WebTask

/**
 * Interface provides operations for manipulated [WebTask] data.
 */
interface WebTaskService {
    /**
     * Saves entity.
     * @param webTask must be non-null
     * @return saved entity.
     */
    fun save(webTask: WebTask): WebTask

    /**
     * Returns all saved entities.
     * @return all saved entities.
     */
    fun getAll(): List<WebTask>

    /**
     * Deletes the entity with the given id.
     *
     * @param uuid must not be {@literal null}.
     */
    fun delete(uuid: String)
}