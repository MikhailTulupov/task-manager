package com.example.taskmanager.model.web

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * This class presents canal model for deserialization and serialization
 * [com.example.taskmanager.model.Task] for next use.
 */
class WebTask(
    var id: String? = null,
    var name: String,
    var description: String,
    var date: String,
    @JsonAlias(value = ["tagId"])
    @JsonProperty(value = "tagId")
    var tag: String? = null
) : Serializable {
    /**
     * This inner class represent builder pattern.
     */
    class Builder(
        private var id: String? = null,
        private var name: String = "",
        private var description: String = "",
        private var date: String = "",
        private var tag: String? = null
    ) {
        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun date(date: String) = apply { this.date = date }
        fun tag(tag: String?) = apply { this.tag = tag }
        fun build() = WebTask(id, name, description, date, tag)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WebTask

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}