package com.xpromus.okanefinancespring.core.sorting.tags

import com.xpromus.okanefinancespring.core.sorting.tags.dtos.CreateTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.EditTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.dtos.GetTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.mapper.fromCreateTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.mapper.fromEditTagDto
import com.xpromus.okanefinancespring.core.sorting.tags.mapper.toGetTagDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagService(
    private val tagRepository: TagRepository,
) {
    fun getTagById(id: UUID): Tag {
        return tagRepository.findById(id).orElseGet {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Tag", id.toString())
            )
        }
    }

    fun getAllTags(id: UUID?, tagName: String?): List<GetTagDto> {
        val tagsToReturn = tagRepository.findTagsByFields(id, tagName)
        return tagsToReturn.map { toGetTagDto(it) }
    }

    fun createTag(createTagDto: CreateTagDto): GetTagDto {
        val newTag = tagRepository.save(fromCreateTagDto(createTagDto))
        return toGetTagDto(newTag)
    }

    fun updateTag(id: UUID, editTagDto: EditTagDto): GetTagDto {
        return tagRepository.findById(id).map {
            val save = tagRepository.save(fromEditTagDto(it, editTagDto))
            toGetTagDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteTag(id: UUID) {
        val toDeleteTag = getTagById(id)
        tagRepository.delete(toDeleteTag)
    }
}