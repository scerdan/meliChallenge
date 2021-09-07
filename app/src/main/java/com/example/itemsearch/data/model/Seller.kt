package com.example.itemsearch.data.model

data class Seller(
    val car_dealer: Boolean,
    val car_dealer_logo: String,
    val eshop: Eshop,
    val home_image_url: String,
    val id: Int,
    val permalink: String,
    val real_estate_agency: Boolean,
    val registration_date: String,
    val seller_reputation: SellerReputation,
    val tags: List<String>
)