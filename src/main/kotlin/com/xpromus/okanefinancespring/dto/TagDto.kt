package com.xpromus.okanefinancespring.dto

import com.xpromus.okanefinancespring.entities.Tag
import java.util.*

data class TagDto(
    val tagName: String
)

fun convertTagDtoToTag(tagDto: TagDto): Tag {
    return Tag(
        id = UUID.randomUUID(),
        tagName = tagDto.tagName
    )
}
