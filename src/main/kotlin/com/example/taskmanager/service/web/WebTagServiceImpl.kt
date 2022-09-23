package com.example.taskmanager.service.web

import com.example.taskmanager.mapper.tagToWebTag
import com.example.taskmanager.mapper.webTagToTag
import com.example.taskmanager.model.web.WebTag
import com.example.taskmanager.service.TagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class WebTagServiceImpl @Autowired constructor(
    val tagService: TagService
): WebTagService {
    override fun save(webTag: WebTag): WebTag = tagService.add(webTag.webTagToTag()).tagToWebTag()

    override fun getById(uuid: String): WebTag = tagService.findById(UUID.fromString(uuid)).tagToWebTag()

    override fun deleteById(uuid: String) = tagService.deleteById(UUID.fromString(uuid))
}