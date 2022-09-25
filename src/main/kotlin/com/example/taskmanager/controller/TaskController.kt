package com.example.taskmanager.controller

import com.example.taskmanager.model.web.WebTask
import com.example.taskmanager.service.web.WebTaskService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Controller process user request.
 */
@RestController
class TaskController @Autowired constructor(
    val webTaskService: WebTaskService
) {
    /**
     * Save post entity in repository.
     * @param webTask task in json format.
     * @return Saved entity.
     */
    @Operation(summary = "Add or update task")
    @PostMapping("/task")
    fun post(@RequestBody webTask: WebTask) = webTaskService.save(webTask)

    /**
     * Returns all saved entities.
     * @return all saved entities.
     */
    @Operation(summary = "Return list of tasks")
    @GetMapping("/tasks")
    fun getAll() = webTaskService.getAll()

    /**
     * Deletes the entity with the given id.
     * @param id must not be non-null.
     */
    @Parameters(
        value = [
            Parameter(
                name = "id",
                description = "The ID task witch we are delete",
                content = [
                    Content(
                        schema = Schema(
                            type = "string",
                            format = "uuid"
                        )
                    )
                ]
            )
        ]
    )
    @DeleteMapping("/task/{id}")
    fun deleteById(@PathVariable id: String) = webTaskService.delete(id)
}