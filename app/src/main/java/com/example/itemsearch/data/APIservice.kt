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



//        Call<Model> getRoms_center(@Query("company_name") String name);
//    ): Response<Item>

//    @GET("today.json")
//    fun getSalahTiming(
//        @Query("latitude") latitude: Double,
//        @Query("longitude") longitude: Double,
//        @Query("elevation") elevation: Int,
//        @Query("timeformat") timeformat: Int,
//    ): Call<SalahMainResponse?>?

}