package com.xpromus.okane_finance_spring.payee

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayeeService @Autowired constructor(
    private val payeeRepository: PayeeRepository
) {
    fun getPayeeById(id: UUID): Payee? {
        return payeeRepository.findPayeeById(id)
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
    fun deletePayee(payee: Payee) {
        payeeRepository.delete(payee)
    }

    fun updatePayee(payeeDto: PayeeDto, id: UUID): Payee {
        return payeeRepository.findById(id).map {
            val save = payeeRepository.save(Payee(
                id = it.id,
                payeeName = payeeDto.payeeName,
                transactions = it.transactions
            ))
            Payee(
                id = save.id,
                payeeName = save.payeeName,
                transactions = save.transactions
            )
        }.orElseGet(null)
    }
}