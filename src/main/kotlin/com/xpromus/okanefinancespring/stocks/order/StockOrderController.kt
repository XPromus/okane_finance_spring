package com.xpromus.okanefinancespring.stocks.order

import com.xpromus.okanefinancespring.stocks.order.dtos.CreateStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.dtos.EditStockOrderDto
import com.xpromus.okanefinancespring.stocks.order.dtos.GetStockOrderDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/stock-order")
class StockOrderController(
    private val stockOrderService: StockOrderService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getStockOrders(
        @RequestParam(name = "id", required = false) id: UUID?,
        @RequestParam(name = "isin", required = false) isin: String?,
        @RequestParam(name = "wkn", required = false) wkn: String?,
        @RequestParam(name = "tickerSymbol", required = false) tickerSymbol: String?,
        @RequestParam(name = "stockName", required = false) stockName: String?,
        @RequestParam(name = "numberOfStocks", required = false) numberOfStocks: Int?,
        @RequestParam(name = "buyInPrice", required = false) buyInPrice: Long?,
        @RequestParam(name = "fees", required = false) fees: Long?,
        @RequestParam(name = "tradeDate", required = false) tradeDate: Date?,
        @RequestParam(name = "targetWithdrawAccountId", required = false) targetWithdrawAccountId: UUID?,
        @RequestParam(name = "targetDepotId", required = false) targetDepotId: UUID?
    ): List<GetStockOrderDto> {
        return stockOrderService.getAllStockOrders(
            id, isin, wkn, tickerSymbol, stockName, numberOfStocks, buyInPrice, fees, tradeDate, targetWithdrawAccountId, targetDepotId
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createStockOrder(
        @RequestBody createStockOrderDto: CreateStockOrderDto
    ): GetStockOrderDto {
        return stockOrderService.createStockOrder(createStockOrderDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateStockOrder(
        @PathVariable id: UUID,
        @RequestBody editStockOrderDto: EditStockOrderDto
    ): GetStockOrderDto {
        return stockOrderService.updateStockOrder(id, editStockOrderDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStockOrder(
        @PathVariable id: UUID
    ) {
        stockOrderService.deleteStockOrder(id)
    }
}