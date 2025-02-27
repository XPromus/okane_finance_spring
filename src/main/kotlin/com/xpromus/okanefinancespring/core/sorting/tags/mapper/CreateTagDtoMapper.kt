package com.xpromus.okanefinancespring.core.sorting.tags.mapper

import com.xpromus.okanefinancespring.core.sorting.tags.Tag
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.CreateTagDto

fun fromCreateTagDto(createTagDto: CreateTagDto): Tag {
    return Tag(
        tagName = createTagDto.tagName
    )
}
