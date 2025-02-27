package com.xpromus.okanefinancespring.stocks.taxexemption.maximum

data class GetTaxExemptionMaximumDto (
    val status: Boolean,
    val valueOfAllEntries: Int,
    val maxValue: Int
)