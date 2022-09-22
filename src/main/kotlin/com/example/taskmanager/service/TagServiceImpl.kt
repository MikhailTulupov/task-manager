package com.example.taskmanager.service

import com.example.taskmanager.model.Tag
import com.example.taskmanager.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Implementation [TagService] interface.
 */
@Service
class TagServiceImpl @Autowired constructor(
    val repository: TagRepository
) : TagService {
    override fun add(tag: Tag): Tag = repository.save(tag)

    override fun update(tag: Tag): Tag = repository.save(tag)

    override fun findById(id: UUID): Tag {
        val tagOptional = repository.findById(id)
        return tagOptional.orElse(Tag.Builder().build())
    }

    override fun deleteById(id: UUID) = repository.deleteById(id)
}