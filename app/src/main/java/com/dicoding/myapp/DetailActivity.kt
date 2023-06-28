package com.dicoding.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.myapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val data = intent.getParcelableExtra<Member>("DATA")

        if (data != null) {
            Glide.with(this).load(data.photo).into(binding.imgItemPhoto)
            binding.tvItemName.text = data.name.toString()
            binding.tvItemBirthdate2.text = data.birthdate.toString()
            binding.tvItemDescription2.text = data.description.toString()
            binding.tvItemEmoji2.text = data.emoji.toString()
        }
    }
}