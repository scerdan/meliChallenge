package com.example.itemsearch.model

data class SellerReputation(
    val level_id: String,
    val metrics: Metrics,
    val power_seller_status: Any,
    val transactions: Transactions
)