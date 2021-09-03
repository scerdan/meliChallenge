package com.example.itemsearch.ui

import android.content.Context
import android.content.Intent
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
        val title: TextView = binding.tvTitle
        val img: ImageView = binding.ivItems
        //val subTitle = binding.tvSubTitle
        private val cardViewItem = binding.cvItemCard
        private val context: Context = view.context

        init {
            cardViewItem.setOnClickListener { v ->
                val int = Intent(v.context, DetailActivity::class.java)
                context.startActivity(int)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = elementSearch[position]
        holder.title.text = item[0]
        //holder.subTitle.text = item[3]
        Picasso.get().load(item[1]).into(holder.img)
    }
}