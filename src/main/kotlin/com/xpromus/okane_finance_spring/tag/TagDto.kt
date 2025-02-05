package com.xpromus.okane_finance_spring.tag

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
