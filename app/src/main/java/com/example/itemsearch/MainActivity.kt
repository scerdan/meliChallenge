package com.example.itemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemsearch.data.APIservice
import com.example.itemsearch.databinding.ActivityMainBinding
import com.example.itemsearch.model.Item
import com.example.itemsearch.ui.ItemAdapter
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
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

    private fun searchItem(url: String) {
        val api = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/MLA/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIservice::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getSearchItem(url)
            var rta = response.body()?.results
            var gson = Gson()
            val dto = gson.toJson(rta)
            val gg = dto[0]


            runOnUiThread {
                print(gg.toString())
                Log.e("VER", gg.toString())

//                Log.e("200", dto.length.toString())

            }
        }
    }

}


