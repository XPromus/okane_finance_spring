package com.xpromus.okanefinancespring.core.sorting.tags.mapper

import com.xpromus.okanefinancespring.core.sorting.tags.Tag
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.EditTagDto

fun fromEditTagDto(tag: Tag, editTagDto: EditTagDto): Tag {
    return Tag(
        id = tag.id,
        tagName = editTagDto.tagName ?: tag.tagName,
        transactions = tag.transactions
    )
}
