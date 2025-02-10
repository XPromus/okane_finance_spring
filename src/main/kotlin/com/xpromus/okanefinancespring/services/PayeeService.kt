package com.xpromus.okanefinancespring.services

import com.xpromus.okanefinancespring.dto.PayeeDto
import com.xpromus.okanefinancespring.entities.Payee
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.mapper.convertPayeeDtoToPayee
import com.xpromus.okanefinancespring.repositories.PayeeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayeeService(
    private val payeeRepository: PayeeRepository,
) {
    fun getPayeeById(id: UUID): Payee {
        return payeeRepository.findById(id).orElseGet {
            throw EntityNotFoundException("Payee with id $id could not be found")
        }
    }

    fun getAllPayees(id: UUID?, payeeName: String?): List<Payee> {
        return payeeRepository.findPayeesByFields(id, payeeName)
    }

    fun createPayee(payeeDto: PayeeDto): Payee {
        return payeeRepository.save(convertPayeeDtoToPayee(payeeDto))
    }

    @Transactional
    fun deletePayee(id: UUID) {
        val toDeletePayee = getPayeeById(id)
        payeeRepository.delete(toDeletePayee)
    }

    fun updatePayee(payeeDto: PayeeDto, id: UUID): Payee {
        return payeeRepository.findById(id).map {
            val save = payeeRepository.save(
                Payee(
                    id = it.id,
                    payeeName = payeeDto.payeeName,
                    transactions = it.transactions
                )
            )
            Payee(
                id = save.id,
                payeeName = save.payeeName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }

}