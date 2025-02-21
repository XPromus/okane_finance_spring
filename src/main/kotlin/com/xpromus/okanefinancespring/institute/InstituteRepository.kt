package com.xpromus.okanefinancespring.institute

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface InstituteRepository : JpaRepository<Institute, UUID> {

    @Query(
        "SELECT i FROM institute i WHERE " +
                "(:id IS NULL OR i.id = :id) AND" +
                "(:name IS NULL OR i.name = :name)"
    )
    fun findInstitutesByFields(
        id: UUID?,
        name: String?
    ): MutableList<Institute>
}