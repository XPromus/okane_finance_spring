package com.xpromus.okanefinancespring

import com.xpromus.okanefinancespring.dto.OwnerDto
import com.xpromus.okanefinancespring.mapper.convertOwnerDtoToOwner
import com.xpromus.okanefinancespring.services.OwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class OwnerTests {

    @Autowired
    private lateinit var ownerService: OwnerService

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