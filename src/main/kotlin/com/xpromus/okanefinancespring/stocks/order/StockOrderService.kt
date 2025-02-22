package com.xpromus.okanefinancespring.stocks.order

import com.xpromus.okanefinancespring.exceptions.EntityNotFoundException
import com.xpromus.okanefinancespring.stocks.depot.DepotService
import com.xpromus.okanefinancespring.util.getEntityNotFoundExceptionMessage
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockOrderService(
    private val stockOrderRepository: StockOrderRepository,
    private val depotService: DepotService
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
        stockName: String?,
        numberOfStocks: Int?,
        buyInPrice: Long?,
        fees: Long?,
        tradeDate: Date?,
        targetDepotId: UUID?
    ): List<StockOrder> {
        return stockOrderRepository.findStockOrdersByFields(
            id,
            isin,
            wkn,
            stockName,
            numberOfStocks,
            buyInPrice,
            fees,
            tradeDate,
            targetDepotId
        )
    }

    fun createStockOrder(stockOrderDto: StockOrderDto): StockOrder {
        val targetDepot = depotService.getDepotById(UUID.fromString(stockOrderDto.targetDepotId))
        return stockOrderRepository.save(
            convertStockOrderDtoToStockOrder(stockOrderDto, targetDepot)
        )
    }

    @Transactional
    fun deleteStockOrder(id: UUID) {
        val toDeleteStockOrder = getStockOrderById(id)
        stockOrderRepository.delete(toDeleteStockOrder)
    }

    fun updateStockOrder(stockOrderDto: StockOrderDto, id: UUID): StockOrder {
        val targetDepot = depotService.getDepotById(UUID.fromString(stockOrderDto.targetDepotId))
        return stockOrderRepository.findById(id).map {
            val save = stockOrderRepository.save(
                StockOrder(
                    id = it.id,
                    isin = stockOrderDto.isin,
                    wkn = stockOrderDto.wkn,
                    stockName = stockOrderDto.stockName,
                    numberOfStocks = stockOrderDto.numberOfStocks,
                    buyInPrice = stockOrderDto.buyInPrice,
                    fees = stockOrderDto.fees,
                    tradeDate = stockOrderDto.tradeDate,
                    targetDepot = targetDepot
                )
            )
            StockOrder(
                id = save.id,
                isin = save.isin,
                wkn = save.wkn,
                stockName = save.stockName,
                numberOfStocks = save.numberOfStocks,
                buyInPrice = save.buyInPrice,
                fees = save.fees,
                tradeDate = save.tradeDate,
                targetDepot = save.targetDepot
            )
        }.orElseGet(null)
    }

}