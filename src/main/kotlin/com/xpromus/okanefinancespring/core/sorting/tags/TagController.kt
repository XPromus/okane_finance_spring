package com.xpromus.okanefinancespring.core.sorting.tags

import com.xpromus.okanefinancespring.core.sorting.tags.dtos.CreateTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.EditTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.GetTagDto
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
    ): List<GetTagDto> {
        return tagService.getAllTags(id, tagName)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTag(@RequestBody createTagDto: CreateTagDto): GetTagDto {
        return tagService.createTag(createTagDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTag(
        @PathVariable id: UUID,
        @RequestBody editTagDto: EditTagDto
    ): GetTagDto {
        return tagService.updateTag(id, editTagDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTag(
        @PathVariable id: UUID
    ) {
        tagService.deleteTag(id)
    }
}