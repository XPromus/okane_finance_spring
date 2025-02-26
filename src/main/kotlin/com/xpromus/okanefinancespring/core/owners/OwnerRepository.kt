package com.xpromus.okanefinancespring.core.owners

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Date
import java.util.UUID

@Repository
interface OwnerRepository : JpaRepository<Owner, UUID> {

    @Query(
        "SELECT o FROM owner o WHERE " +
                "(:id IS NULL OR o.id = :id) AND" +
                "(:firstName IS NULL OR o.firstName = :firstName) AND" +
                "(:lastName IS NULL OR o.lastName = :lastName) AND" +
                "(:birthday IS NULL OR o.birthday = :birthday)"
    )
    fun findOwnersByFields(
        id: UUID?,
        firstName: String?,
        lastName: String?,
        birthday: Date?,
    ): MutableList<Owner>

}