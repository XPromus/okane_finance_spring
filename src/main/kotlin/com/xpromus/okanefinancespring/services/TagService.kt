package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.TagDto
import com.xpromus.okanefinancespring.dto.convertTagDtoToTag
import com.xpromus.okanefinancespring.entities.Tag
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.repositories.TagRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TagService @Autowired constructor(
    private val tagRepository: TagRepository
) {

    fun getTagById(id: UUID): Tag {
        return tagRepository.findById(id).orElseGet {
            throw EntityNotFoundException("Tag with id $id could not be found")
        }
    }

    fun getAllTags(id: UUID?, tagName: String?): List<Tag> {
        if ((id ?: tagName) != null) {
            return tagRepository.findTagsByIdAndTagName(id, tagName)
        }

        return tagRepository.findAll()
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