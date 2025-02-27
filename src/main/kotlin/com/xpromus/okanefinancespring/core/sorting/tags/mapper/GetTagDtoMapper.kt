package com.xpromus.okanefinancespring.core.sorting.tags.mapper

import com.xpromus.okanefinancespring.core.sorting.tags.Tag
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.GetTagDto

fun toGetTagDto(tag: Tag): GetTagDto {
    return GetTagDto(
        id = tag.id!!,
        tagName = tag.tagName,
        transactionIDs = tag.transactions.map { it.id!! }
    )
}
