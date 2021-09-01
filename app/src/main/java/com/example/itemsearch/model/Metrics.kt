package com.example.itemsearch.model

data class Metrics(
    val cancellations: Cancellations,
    val claims: Claims,
    val delayed_handling_time: DelayedHandlingTime,
    val sales: Sales
)