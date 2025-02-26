package com.xpromus.okanefinancespring.core.sorting.tags

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

    fun getAllTags(id: UUID?, tagName: String?): List<Tag> {
        return tagRepository.findTagsByFields(id, tagName)
    }

    fun createTag(tagDto: TagDto): Tag {
        return tagRepository.save(convertTagDtoToTag(tagDto))
    }

    @Transactional
    fun deleteTag(id: UUID) {
        val toDeleteTag = getTagById(id)
        tagRepository.delete(toDeleteTag)
    }

    fun updateTag(tagDto: TagDto, id: UUID): Tag {
        return tagRepository.findById(id).map {
            val save = tagRepository.save(
                Tag(
                    id = it.id,
                    tagName = tagDto.tagName,
                    transactions = it.transactions
                )
            )
            Tag(
                id = save.id,
                tagName = save.tagName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }

}