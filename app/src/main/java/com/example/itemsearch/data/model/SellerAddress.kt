package com.example.itemsearch.data.model

data class SellerAddress(
    val address_line: String,
    val city: CityX,
    val comment: String,
    val country: CountryX,
    val id: String,
    val latitude: String,
    val longitude: String,
    val state: StateX,
    val zip_code: String
)