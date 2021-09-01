package com.example.itemsearch.model

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX>
)