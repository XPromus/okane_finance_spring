package com.xpromus.okanefinancespring.core.sorting.categories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {
    @Query(
        "SELECT c FROM category c WHERE " +
                "(:id IS NULL OR c.id = :id) AND " +
                "(:categoryName IS NULL OR c.categoryName = :categoryName)"
    )
    fun findCategoriesByFields(
        id: UUID?,
        categoryName: String?
    ): MutableList<Category>

}