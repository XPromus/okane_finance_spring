package com.xpromus.okanefinancespring.stocks.order

import com.xpromus.okanefinancespring.core.accounts.AccountService
import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.stocks.depot.DepotService
import com.xpromus.okanefinancespring.stocks.order.dtos.CreateStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.dtos.EditStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.dtos.GetStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.mapper.fromCreateStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.mapper.fromEditStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.mapper.toGetStockOrderDto
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockOrderService(
    private val stockOrderRepository: StockOrderRepository,
    private val depotService: DepotService,
    private val accountService: AccountService
) {
    fun getStockOrderById(id: UUID): StockOrder {
        return stockOrderRepository.findById(id).orElseThrow {
            throw EntityNotFoundException(
                getEntityNotFoundExceptionMessage("StockOrder", id.toString())
            )
        }
    }

    fun getAllStockOrders(
        id: UUID?,
        isin: String?,
        wkn: String?,
        tickerSymbol: String?,
        stockName: String?,
        numberOfStocks: Int?,
        buyInPrice: Long?,
        fees: Long?,
        tradeDate: Date?,
        targetWithdrawAccountId: UUID?,
        targetDepotId: UUID?
    ): List<GetStockOrderDto> {
        val stockOrdersToReturn = stockOrderRepository.findStockOrdersByFields(
            id,
            isin,
            wkn,
            tickerSymbol,
            stockName,
            numberOfStocks,
            buyInPrice,
            fees,
            tradeDate,
            targetWithdrawAccountId,
            targetDepotId
        )
        return stockOrdersToReturn.map { toGetStockOrderDto(it) }
    }

    fun createStockOrder(createStockOrderDto: CreateStockOrderDto): GetStockOrderDto {
        val targetWithdrawAccount = accountService.getAccountById(createStockOrderDto.targetWithdrawAccountID)
        val targetDepot = depotService.getDepotById(createStockOrderDto.targetDepotID)
        val newStockOrder = stockOrderRepository.save(
            fromCreateStockOrderDto(targetWithdrawAccount, targetDepot, createStockOrderDto)
        )
        return toGetStockOrderDto(newStockOrder)
    }

    fun updateStockOrder(id: UUID, editStockOrderDto: EditStockOrderDto): GetStockOrderDto {
        val targetWithdrawAccount = if (editStockOrderDto.targetWithdrawAccountID == null) {
            null
        } else {
            accountService.getAccountById(editStockOrderDto.targetWithdrawAccountID)
        }

        val targetDepot = if (editStockOrderDto.targetDepotID == null) {
            null
        } else {
            depotService.getDepotById(editStockOrderDto.targetDepotID)
        }

        return stockOrderRepository.findById(id).map {
            val save = stockOrderRepository.save(
                fromEditStockOrderDto(it, targetWithdrawAccount, targetDepot, editStockOrderDto)
            )
            toGetStockOrderDto(save)
        }.orElseGet(null)
    }

    @Transactional
    fun deleteStockOrder(id: UUID) {
        val toDeleteStockOrder = getStockOrderById(id)
        stockOrderRepository.delete(toDeleteStockOrder)
    }
}