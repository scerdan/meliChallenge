package com.example.itemsearch.model

data class Location(
    val address_line: String,
    val city: City,
    val country: Country,
    val latitude: Double,
    val longitude: Double,
    val neighborhood: Neighborhood,
    val state: State,
    val subneighborhood: Any,
    val zip_code: String
)