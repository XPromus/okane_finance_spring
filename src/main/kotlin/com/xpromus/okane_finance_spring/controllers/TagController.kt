package com.xpromus.okane_finance_spring.controllers

import com.xpromus.okane_finance_spring.dto.TagDto
import com.xpromus.okane_finance_spring.entities.Tag
import com.xpromus.okane_finance_spring.services.TagService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tags")
class TagController(
    private val tagService: TagService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTags(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "tagName", required = false) tagName: String?
    ): List<Tag> {
        return tagService.getAllTags(id, tagName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTag(@RequestBody tagDto: TagDto): Tag {
        return tagService.createTag(tagDto)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTag(
        @RequestParam(name = "id", required = true) id: UUID
    ) {
        tagService.deleteTag(id)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTag(
        @RequestBody tagDto: TagDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): Tag {
        return tagService.updateTag(tagDto, id)
    }

}