package com.example.taskmanager.controller

import com.example.taskmanager.model.web.WebTag
import com.example.taskmanager.service.web.WebTagService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Controller process user request.
 */
@RestController
class TagController @Autowired constructor(
    val webTagService: WebTagService
) {
    /**
     * Save post entity in repository.
     * @param webTag tag in json format.
     * @return Saved entity.
     */
    @Operation(summary = "Add or update tag")
    @PostMapping("/tag")
    fun post(@RequestBody webTag: WebTag) = webTagService.save(webTag)

    /**
     * Retrieves an entity by its id.
     * @param id must be non-null.
     * @return the entity with the given id or none found.
     */
    @Operation(summary = "Get tag by id and all its tasks")
    @Parameters(
        value = [
            Parameter(
                name = "id",
                description = "The ID is a Universal Unique Identifier " +
                        "(UUID). This ID will always be unique.",
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
    @GetMapping("/tag/{id}")
    fun getById(@PathVariable(value = "id") id: String) = webTagService.getById(id)

    /**
     * Deletes the entity with the given id.
     * @param id must not be.
     */
    @Operation(summary = "Delete tag by id and cascade delete all its tasks")
    @Parameters(
        value = [
            Parameter(
                name = "id",
                description = "The ID tag witch we are delete",
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
    @DeleteMapping("/tag/{id}")
    fun deleteById(@PathVariable id: String) = webTagService.deleteById(id)
}