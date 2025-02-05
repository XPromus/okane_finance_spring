package com.xpromus.okane_finance_spring.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TagRepository : JpaRepository<Tag, UUID> {

    @Query("SELECT t FROM tag t WHERE t.id = ?1")
    fun findTagById(id: UUID): Tag?

    @Query(
        "SELECT t FROM tag t WHERE " +
        "(:id IS NULL OR t.id = :id) AND " +
        "(:tagName IS NULL OR t.tagName = :tagName)"
    )
    fun findTagsByIdAndTagName(
        id: UUID?,
        tagName: String?
    ): MutableList<Tag>

}