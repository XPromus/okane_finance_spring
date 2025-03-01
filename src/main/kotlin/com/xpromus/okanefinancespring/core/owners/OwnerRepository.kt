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
                "(cast(:id as uuid) IS NULL OR o.id = :id) AND" +
                "(cast(:firstName as string) IS NULL OR o.firstName = :firstName) AND" +
                "(cast(:lastName as string) IS NULL OR o.lastName = :lastName) AND" +
                "(cast(:birthday as timestamp) IS NULL OR o.birthday = :birthday)"
    )
    fun findOwnersByFields(
        id: UUID?,
        firstName: String?,
        lastName: String?,
        birthday: Date?,
    ): MutableList<Owner>

}