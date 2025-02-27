package com.xpromus.okanefinancespring.core.payees

import com.xpromus.okanefinancespring.core.payees.dtos.CreatePayeeDto
import com.xpromus.okanefinancespring.core.payees.dtos.EditPayeeDto
import com.xpromus.okanefinancespring.core.payees.dtos.GetPayeeDto
import com.xpromus.okanefinancespring.core.payees.mapper.fromCreatePayeeDto
import com.xpromus.okanefinancespring.core.payees.mapper.fromEditPayeeDto
import com.xpromus.okanefinancespring.core.payees.mapper.toGetPayeeDto
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayeeService(
    private val payeeRepository: PayeeRepository,
) {
    fun getPayeeById(id: UUID): Payee {
        return payeeRepository.findById(id).orElseGet {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("Payee", id.toString())
            )
        }
    }

    fun getAllPayees(id: UUID?, payeeName: String?): List<GetPayeeDto> {
        val payeesToReturn = payeeRepository.findPayeesByFields(id, payeeName)
        return payeesToReturn.map { toGetPayeeDto(it) }
    }

    fun createPayee(createPayeeDto: CreatePayeeDto): GetPayeeDto {
        val newPayee = payeeRepository.save(fromCreatePayeeDto(createPayeeDto))
        return toGetPayeeDto(newPayee)
    }

    fun updatePayee(id: UUID, editPayeeDto: EditPayeeDto): GetPayeeDto {
        return payeeRepository.findById(id).map {
            val save = payeeRepository.save(fromEditPayeeDto(it, editPayeeDto))
            toGetPayeeDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deletePayee(id: UUID) {
        val toDeletePayee = getPayeeById(id)
        payeeRepository.delete(toDeletePayee)
    }
}