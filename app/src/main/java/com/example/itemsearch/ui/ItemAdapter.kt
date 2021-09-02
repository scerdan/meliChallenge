package com.example.itemsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itemsearch.R
import com.example.itemsearch.databinding.ItemBinding
import com.squareup.picasso.Picasso

class ItemAdapter(private val elementSearch: MutableList<ArrayList<String>>) :
    RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = elementSearch.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)
        var title: TextView = binding.tvTitle
        var img: ImageView = binding.ivItems

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = elementSearch[position]
        holder.title.text = item[0]
        Picasso.get().load(item[1]).into(holder.img)
    }
}