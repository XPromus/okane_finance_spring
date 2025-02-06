package com.xpromus.okanefinancespring.controllers

import com.xpromus.okanefinancespring.dto.TagDto
import com.xpromus.okanefinancespring.entities.Tag
import com.xpromus.okanefinancespring.services.TagService
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTag(
        @PathVariable id: UUID
    ) {
        tagService.deleteTag(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTag(
        @PathVariable id: UUID,
        @RequestBody tagDto: TagDto
    ): Tag {
        return tagService.updateTag(tagDto, id)
    }

    @PutMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.OK)
    fun addTransactions(
        @PathVariable id: UUID,
        @RequestBody transactions: List<UUID>
    ): Tag {
        return tagService.addTransactions(transactions, id)
    }

}