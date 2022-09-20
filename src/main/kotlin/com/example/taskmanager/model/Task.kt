package com.example.taskmanager.model

import java.util.*

/**
 * This class allows to create instance this class.
 * You can add task name, add description and add special tag.
 */
class Task(
    var id: UUID? = null,
    var name: String,
    var description: String,
    var date: Date?,
    var tag: Tag?
) {
    /**
     * This inner class represent builder pattern.
     */
    class Builder(
        private var id: UUID? = null,
        private var name: String = "",
        private var description: String = "",
        private var date: Date? = null,
        private var tag: Tag? = null
    ) {
        fun id(id: UUID) = apply { this.id = null }
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun date(date: Date) = apply { this.date = date }
        fun tag(tag: Tag) = apply { this.tag = tag }
        fun build() = Task(id, name, description, date, tag)
    }
}