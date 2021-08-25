package com.example.itemsearch.model

data class Mobile(
    val available_filters: List<Any>,
    val available_sorts: List<Any>,
    val country_default_time_zone: String,
    val filters: List<Any>,
    val results: List<Result>,
    val site_id: String,
)