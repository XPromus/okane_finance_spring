package com.xpromus.okanefinancespring.mapper

import com.xpromus.okanefinancespring.dto.TagDto
import com.xpromus.okanefinancespring.entities.Tag

fun convertTagDtoToTag(
    tagDto: TagDto
): Tag {
    return Tag(
        tagName = tagDto.tagName
    )
}
