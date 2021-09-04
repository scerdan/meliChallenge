package com.example.itemsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itemsearch.databinding.ItemBinding
import com.example.itemsearch.ui.OnItemSearchClick
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val context: Context,
    private val elementSearch: MutableList<ArrayList<String>>,
    val itemClickListener: OnItemSearchClick
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = elementSearch[position]
        when (holder) {
            is MyViewHolder -> holder.bin(item, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int = elementSearch.size

    inner class MyViewHolder(itemView: View) :
        BaseViewHolder<MutableList<ArrayList<String>>>(itemView) {
        override fun bin(item: ArrayList<String>, position: Int) {

            val binding = ItemBinding.bind(itemView)
            val cardViewItem = binding.cvItemCard
            val title: TextView = binding.tvTitle
            val img: ImageView = binding.ivItems


            title.text = item[0]
            Picasso.get().load(item[1]).resize(150, 150).centerCrop().into(img)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(item[0], item[1], item[3], item[2])
            }
        }
    }
}