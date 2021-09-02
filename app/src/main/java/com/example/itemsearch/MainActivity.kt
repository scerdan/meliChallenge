package com.example.itemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.itemsearch.databinding.ActivityMainBinding
import com.example.itemsearch.ui.ItemAdapter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

                GlobalScope.launch(Dispatchers.IO) {
                    val rta = response.getJSONArray("results")

                    runOnUiThread {
                        for (i in 0 until rta.length()) {
                            val jsonObject = rta.getJSONObject(i)
                            val titulo = jsonObject.get("title")
                            val thumbnail = jsonObject.get("thumbnail")
                            Log.e(titulo.toString(), thumbnail.toString())
                        }
                    }
                }
            },
            { error ->
                Log.e("ERROR", error.toString())
            })
        queue.add(jsonObjectRequest)
    }
}


