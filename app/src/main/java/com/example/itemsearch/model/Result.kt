package com.example.itemsearch.model

data class Result(
    val accepts_mercadopago: Boolean,
    val address: Address,
    val attributes: List<Attribute>,
    val available_quantity: Int,
    val catalog_product_id: String,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val id: String,
    val permalink: String,
    val price: Int,
    val site_id: String,
    val sold_quantity: Int,
    val thumbnail: String,
    val thumbnail_id: String,
    val title: String
)