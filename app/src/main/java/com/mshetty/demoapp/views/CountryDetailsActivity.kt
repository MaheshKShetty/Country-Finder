package com.mshetty.demoapp.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mshetty.demoapp.databinding.ActivityCountryDetailsBinding

class CountryDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCountryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        setUpClickListner()
    }

    private fun setUpViews() {
        binding.tvName.text = intent.getStringExtra("countryName")
        Glide.with(this).load(intent.getStringExtra("countryFlag")).into(binding.ivFlag)
    }

    private fun setUpClickListner() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}