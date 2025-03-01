package com.xpromus.okanefinancespring.stocks.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StockOrderRepository : JpaRepository<StockOrder, UUID> {

    @Query("SELECT s from StockOrder s WHERE " +
            "(cast(:id as uuid) IS NULL OR s.id = :id) AND " +
            "(cast(:isin as string) IS NULL OR s.isin = :isin) AND " +
            "(cast(:wkn as string) IS NULL OR s.wkn = :wkn) AND " +
            "(cast(:stockName as string) IS NULL OR s.stockName = :stockName) AND " +
            "(cast(:numberOfStocks as int) IS NULL OR s.numberOfStocks = :numberOfStocks) AND " +
            "(cast(:buyInPrice as long) IS NULL OR s.buyInPrice = :buyInPrice) AND " +
            "(cast(:fees as long) IS NULL OR s.fees = :fees) AND " +
            "(cast(:tradeDate as timestamp) IS NULL OR s.tradeDate = :tradeDate) AND " +
            "(cast(:targetDepotId as uuid) IS NULL OR s.targetDepot.id = :targetDepotId)"
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