package com.xpromus.okanefinancespring.stocks.order

import com.xpromus.okanefinancespring.stocks.depot.Depot

fun convertStockOrderDtoToStockOrder(
    stockOrderDto: StockOrderDto,
    targetDepot: Depot
): StockOrder {
    return StockOrder(
        isin = stockOrderDto.isin,
        wkn = stockOrderDto.wkn,
        stockName = stockOrderDto.stockName,
        numberOfStocks = stockOrderDto.numberOfStocks,
        buyInPrice = stockOrderDto.buyInPrice,
        fees = stockOrderDto.fees,
        tradeDate = stockOrderDto.tradeDate,
        targetDepot = targetDepot
    )
}