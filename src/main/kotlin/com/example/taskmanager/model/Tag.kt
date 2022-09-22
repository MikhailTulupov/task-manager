package com.example.taskmanager.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * This class allows group tasks by semantic load.
 */
@Entity
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    var id: UUID?,
    var header: String,
    @OneToMany(
        mappedBy = "tag",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}