package com.xpromus.okane_finance_spring.services

import com.xpromus.okane_finance_spring.dto.PayeeDto
import com.xpromus.okane_finance_spring.dto.convertPayeeDtoToPayee
import com.xpromus.okane_finance_spring.entities.Payee
import com.xpromus.okane_finance_spring.exceptions.EntityNotFoundException
import com.xpromus.okane_finance_spring.repositories.PayeeRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayeeService @Autowired constructor(
    private val payeeRepository: PayeeRepository
) {
    fun getPayeeById(id: UUID): Payee {
        return payeeRepository.findById(id).orElseGet {
            throw EntityNotFoundException("Payee with id $id could not be found")
        }
    }

    fun getAllPayees(id: UUID?, payeeName: String?): List<Payee> {
        if ((id ?: payeeName) != null) {
            return payeeRepository.findPayeeByParameters(id, payeeName)
        }

        return payeeRepository.findAll()
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