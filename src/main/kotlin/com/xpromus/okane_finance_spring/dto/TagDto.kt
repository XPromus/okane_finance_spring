package com.xpromus.okane_finance_spring.dto

import com.xpromus.okane_finance_spring.entities.Tag
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
