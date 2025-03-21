package com.xpromus.okanefinancespring.core.institute

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface InstituteRepository : JpaRepository<Institute, UUID> {

    @Query(
        "SELECT i FROM institute i WHERE " +
                "(cast(:id as uuid) IS NULL OR i.id = :id) AND" +
                "(cast(:instituteName as string) IS NULL OR i.instituteName = :instituteName)"
    )
    fun findInstitutesByFields(
        id: UUID?,
        instituteName: String?
    ): MutableList<Institute>
}