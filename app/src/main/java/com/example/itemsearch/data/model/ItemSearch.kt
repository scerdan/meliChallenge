package com.example.itemsearch.data.model

data class ItemSearch(
    val available_filters: List<AvailableFilter>,
    val available_sorts: List<AvailableSort>,
    val country_default_time_zone: String,
    val filters: List<Filter>,
    val paging: Paging,
    val query: String,
    val results: List<Result>,
    val site_id: String,
    val sort: Sort
)