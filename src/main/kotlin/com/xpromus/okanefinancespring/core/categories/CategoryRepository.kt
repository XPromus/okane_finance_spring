package com.xpromus.okanefinancespring.core.categories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {
    @Query(
        "SELECT c FROM category c WHERE " +
                "(cast(:id as uuid) IS NULL OR c.id = :id) AND " +
                "(cast(:categoryName as string) IS NULL OR c.categoryName = :categoryName)"
    )
    fun findCategoriesByFields(
        id: UUID?,
        categoryName: String?
    ): MutableList<Category>

}