package com.example.taskmanager.model

import java.util.*

/**
 * This class allows group tasks by semantic load.
 */
class Tag(
    var id: UUID?,
    var header: String,
    var tasks: MutableList<Task> = mutableListOf()
) {
    /**
     * This inner class represent builder pattern.
     */
    class Builder(
        private var id: UUID? = null,
        private var header: String = "",
        private var tasks: MutableList<Task> = mutableListOf()
    ) {
        fun id(id: UUID) = apply { this.id = id }
        fun header(header: String) = apply { this.header = header }
        fun tasks(tasks: MutableList<Task>) = apply { this.tasks = tasks }
        fun build() = Tag(id, header, tasks)
    }
}