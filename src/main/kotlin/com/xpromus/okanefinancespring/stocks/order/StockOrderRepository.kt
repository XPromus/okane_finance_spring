package com.xpromus.okanefinancespring.stocks.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StockOrderRepository : JpaRepository<StockOrder, UUID> {

    @Query("SELECT s from StockOrder s WHERE " +
            "(:id IS NULL OR s.id = :id) AND " +
            "(:isin IS NULL OR s.isin = :isin) AND " +
            "(:wkn IS NULL OR s.wkn = :wkn) AND " +
            "(:stockName IS NULL OR s.stockName = :stockName) AND " +
            "(:numberOfStocks IS NULL OR s.numberOfStocks = :numberOfStocks) AND " +
            "(:buyInPrice IS NULL OR s.buyInPrice = :buyInPrice) AND " +
            "(:fees IS NULL OR s.fees = :fees) AND " +
            "(:tradeDate IS NULL OR s.tradeDate = :tradeDate) AND " +
            "(:targetDepotId IS NULL OR s.targetDepot.id = :targetDepotId)"
    )
    fun findStockOrdersByFields(
        id: UUID?,
        isin: String?,
        wkn: String?,
        stockName: String?,
        numberOfStocks: Int?,
        buyInPrice: Long?,
        fees: Long?,
        tradeDate: Date?,
        targetDepotId: UUID?
    ): MutableList<StockOrder>

}