package com.example.itemsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.itemsearch.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgDetail = binding.imgItem
        val titleDetail = binding.tvItem
        val tvLink = binding.tvLink

        paintView(imgDetail, titleDetail, tvLink)
    }

    private fun paintView(img: ImageView, titleDetail: TextView, tvLink: TextView) {
        if (intent.extras != null) {
            Picasso.get().load(intent.getStringExtra("img")).into(img)
            titleDetail.text = intent.getStringExtra("title")
            tvLink.text = intent.getStringExtra("url")
        }
    }
}