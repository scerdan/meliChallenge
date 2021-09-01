package com.example.itemsearch.data

import com.example.itemsearch.model.ItemSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface APIservice {
//    @GET("search?q=${id}#json)
//    suspend fun getItems(@Path("id")): Call<Item>


    @GET("search?q=")

    suspend fun getSearchItem(
        @Query("searchItem") searchItem: String
    ): ItemSearch
}