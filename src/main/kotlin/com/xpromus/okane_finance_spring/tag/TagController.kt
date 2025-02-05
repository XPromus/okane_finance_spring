package com.xpromus.okane_finance_spring.tag

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/tags")
class TagController @Autowired constructor(
    private val tagService: TagService
) {

    @GetMapping
    fun getTags(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "tagName", required = false) tagName: String?
    ): ResponseEntity<List<Tag>> {
        return ResponseEntity<List<Tag>>(
            tagService.getAllTags(id, tagName),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun createTag(@RequestBody tagDto: TagDto): ResponseEntity<Tag> {
        return ResponseEntity<Tag>(
            tagService.createTag(tagDto),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deleteTag(
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<String> {
        val toDeleteTag = tagService.getTagById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        tagService.deleteTag(toDeleteTag)
        return ResponseEntity<String>(
            "Tag with id: $id has been deleted",
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateTag(
        @RequestBody tagDto: TagDto,
        @RequestParam(name = "id", required = true) id: UUID
    ): ResponseEntity<Tag> {
        return ResponseEntity<Tag>(
            tagService.updateTag(tagDto, id),
            HttpStatus.OK
        )
    }

}