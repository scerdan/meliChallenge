package com.example.itemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.itemsearch.data.APIservice
import com.example.itemsearch.databinding.ActivityMainBinding
import com.example.itemsearch.model.ItemSearch
import com.example.itemsearch.model.Result
import com.example.itemsearch.ui.ItemAdapter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    //https://api.mercadolibre.com/sites/MLA/search?q=[...]
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private val itemImgs = mutableListOf<String>()

    val URL: String = "https://api.mercadolibre.com/sites/MLA/search?q="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initRecyclerView()

        searchItem("moto")
    }

    private fun initRecyclerView() {
        binding.rvContainerItems.layoutManager = LinearLayoutManager(this)
        binding.rvContainerItems.adapter = adapter
    }

    private fun searchItem(items: String) {
        val queue = Volley.newRequestQueue(this)
        val URL = "https://api.mercadolibre.com/sites/MLA/search?q=${items}"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, URL, null,
            { response ->
                val gson = Gson()
                val rta = response.getJSONArray("results")
                for (i in 0 until rta.length()) {
                    val jsonObject = rta.getJSONObject(i)
                    val titulo = jsonObject.get("title")
                    val thumbnail = jsonObject.get("thumbnail")
                    Log.e(titulo.toString(), thumbnail.toString())
                }
            },
            { error ->
                Log.e("ERROR", error.toString())
            })

        queue.add(jsonObjectRequest)


//        val api = Retrofit.Builder()
//            .baseUrl("https://api.mercadolibre.com/sites/MLA/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(APIservice::class.java)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = api.getSearchItem(url)
//            val rta = response
//
//            val gson = Gson()
//            val dto = gson.toJson(rta)
//
//            runOnUiThread {
//                Log.e("VER", dto)
//            }
//        }
    }

}


