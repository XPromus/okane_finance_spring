package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TagDto
import com.xpromus.okanefinancespring.entities.Tag
import java.util.*

fun convertTagDtoToTag(tagDto: TagDto): Tag {
    return Tag(
        id = UUID.randomUUID(),
        tagName = tagDto.tagName
    )
}
