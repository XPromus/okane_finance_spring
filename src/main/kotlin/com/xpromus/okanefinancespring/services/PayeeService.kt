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
    private val transactionService: TransactionService,
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

    fun addTransactions(transactions: List<UUID>, payeeId: UUID): Payee {
        val transactionsToBeAdded = transactions.map {
            transactionService.getTransactionById(it)
        }

        return payeeRepository.findById(payeeId).map {
            val save = payeeRepository.save(
                Payee(
                    id = it.id,
                    payeeName = it.payeeName,
                    transactions = it.transactions.union(transactionsToBeAdded).toList()
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