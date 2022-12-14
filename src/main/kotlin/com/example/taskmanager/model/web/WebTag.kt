package com.example.taskmanager.model.web

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * This class presents canal model for deserialization and serialization
 * [com.example.taskmanager.model.Tag] for next use.
 */
@Schema
class WebTag(
    @Schema(
        name = "id",
        title = "Tag identifier",
        type = "String",
        format = "uuid"
    )
    var id: String? = null,
    @Schema(
        name = "header",
        title = "Tag header",
        type = "String",
        example = "MyTag"
    )
    var header: String,
    @ArraySchema(arraySchema =
        Schema(
            name = "tasks",
            title = "List of tasks",
            type = "Array"
        )
    )
    var tasks: List<WebTask> = mutableListOf()
) : Serializable {
    /**
     * This inner class represent builder pattern.
     */
    class Builder(
        private var id: String? = null,
        private var header: String = "",
        private var tasks: MutableList<WebTask> = mutableListOf()
    ) {
        fun id(id: String) = apply { this.id = id }
        fun header(header: String) = apply { this.header = header }
        fun tasks(tasks: MutableList<WebTask>) = apply { this.tasks = tasks }
        fun build() = WebTag(id, header, tasks)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WebTag

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}