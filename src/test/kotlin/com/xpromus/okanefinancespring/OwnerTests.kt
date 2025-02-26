package com.xpromus.okanefinancespring

import com.xpromus.okanefinancespring.owners.OwnerDto
import com.xpromus.okanefinancespring.owners.convertOwnerDtoToOwner
import com.xpromus.okanefinancespring.core.owners.OwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class OwnerTests {

    @Autowired
    private lateinit var ownerService: com.xpromus.okanefinancespring.core.owners.OwnerService

    @Test
    fun `should create owner and return it`() {
        val ownerDto = OwnerDto(
            firstName = "John",
            lastName = "Doe",
            birthday = Date()
        )
        val createdOwner = convertOwnerDtoToOwner(ownerDto)
        val request = ownerService.createOwner(ownerDto)
        assertEquals(request.firstName, createdOwner.firstName)
        assertEquals(request.lastName, createdOwner.lastName)
        assertEquals(request.birthday, createdOwner.birthday)
    }

}