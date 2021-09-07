package com.example.itemsearch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.itemsearch.ItemAdapter
import com.example.itemsearch.data.remote.MySingletonVolley
import com.example.itemsearch.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, OnItemSearchClick {

    //https://api.mercadolibre.com/sites/MLA/search?q=[...]
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ItemAdapter
    private val itemsAdd = mutableListOf<ArrayList<String>>()
    private lateinit var pbIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pbIndicator = binding.piLoad
        binding.svSearchItems.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        val recyclerV = binding.rvContainerItems
        recyclerV.layoutManager = LinearLayoutManager(this)
        mAdapter = ItemAdapter(this, itemsAdd, this)
        recyclerV.adapter = mAdapter
    }

    @DelicateCoroutinesApi
    private fun searchItem(items: String) {
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
                            pbIndicator.hide()
                            initRecyclerView()
                        }
                    }
                }
            },
            { error ->
                Log.e("ERROR", error.toString())
            })
        MySingletonVolley.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            pbIndicator.show()
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

    override fun onItemClick(title: String, img: String, url: String, price: String) {
        val int = Intent(this, DetailActivity::class.java)
        int.putExtra("title", title)
        int.putExtra("img", img)
        int.putExtra("url", url)
        int.putExtra("price", price)
        startActivity(int)
    }
}


