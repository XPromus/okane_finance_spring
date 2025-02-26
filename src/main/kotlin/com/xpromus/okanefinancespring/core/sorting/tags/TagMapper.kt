package com.xpromus.okanefinancespring.core.sorting.tags

fun convertTagDtoToTag(
    tagDto: TagDto
): Tag {
    return Tag(
        tagName = tagDto.tagName
    )
}
