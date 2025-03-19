package com.xpromus.okanefinancespring.stocks.order.mapper

import com.xpromus.okanefinancespring.core.accounts.Account
import com.xpromus.okanefinancespring.stocks.depot.Depot
import com.xpromus.okanefinancespring.stocks.order.StockOrder
import com.xpromus.okanefinancespring.stocks.order.dtos.CreateStockOrderDto

fun fromCreateStockOrderDto(
    targetWithdrawAccount: Account,
    targetDepot: Depot,
    createStockOrderDto: CreateStockOrderDto
): StockOrder {
    return StockOrder(
        isin = createStockOrderDto.isin,
        wkn = createStockOrderDto.wkn,
        tickerSymbol = createStockOrderDto.tickerSymbol,
        stockName = createStockOrderDto.stockName,
        numberOfStocks = createStockOrderDto.numberOfStocks,
        buyInPrice = createStockOrderDto.buyInPrice,
        fees = createStockOrderDto.fees,
        tradeDate = createStockOrderDto.tradeDate,
        targetWithdrawAccount = targetWithdrawAccount,
        targetDepot = targetDepot
    )
}
