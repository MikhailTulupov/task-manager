package com.example.taskmanager.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * This class allows to create instance this class.
 * You can add task name, add description and add special tag.
 */
@Entity
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    var id: UUID? = null,
    var name: String,
    var description: String,
    var date: Date?,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH]
    )
    @JoinColumn(name = "tag_id")
    var tag: Tag? = null
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
        fun id(id: UUID) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun date(date: Date) = apply { this.date = date }
        fun tag(tag: Tag?) = apply { this.tag = tag }
        fun build() = Task(id, name, description, date, tag)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}