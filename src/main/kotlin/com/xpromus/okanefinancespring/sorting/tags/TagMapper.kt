package com.xpromus.okanefinancespring.sorting.tags

fun convertTagDtoToTag(
    tagDto: TagDto
): Tag {
    return Tag(
        tagName = tagDto.tagName
    )
}
