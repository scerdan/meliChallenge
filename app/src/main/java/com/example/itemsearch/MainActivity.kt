package com.example.itemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.itemsearch.databinding.ActivityMainBinding
import com.example.itemsearch.ui.ItemAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    //https://api.mercadolibre.com/sites/MLA/search?q=[...]
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ItemAdapter
    private val itemsAdd = mutableListOf<ArrayList<String>>()
    private lateinit var pb_indicator: CircularProgressIndicator

    val URL: String = "https://api.mercadolibre.com/sites/MLA/search?q="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pb_indicator = binding.piLoad
        binding.svSearchItems.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        val recyclerV = binding.rvContainerItems
        recyclerV.layoutManager = LinearLayoutManager(this)
        mAdapter = ItemAdapter(this, itemsAdd)
        recyclerV.adapter = mAdapter
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
                            val box = ArrayList<String>(4)
                            val title = jsonObject.get("title")
                            val thumbnail = jsonObject.get("thumbnail")
                            val price = jsonObject.get("price")
                            val url = jsonObject.get("permalink")

                            box.add(0, title.toString())
                            box.add(1, thumbnail.toString())
                            box.add(2, price.toString())
                            box.add(3, url.toString())

                            itemsAdd.add(i, box)
                            Log.e("ver", itemsAdd.toString())
                            pb_indicator.hide()
                            initRecyclerView()
                        }
                        //Log.e("RTA", rta.toString())
                    }
                }
            },
            { error ->
                Log.e("ERROR", error.toString())
            })
        queue.add(jsonObjectRequest)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            pb_indicator.show()
            searchItem(query.lowercase())
            hideKeyboard()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}


