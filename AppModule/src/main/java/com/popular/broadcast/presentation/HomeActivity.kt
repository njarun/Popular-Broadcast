package com.popular.broadcast.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.popular.broadcast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.actionBar.actionBarToolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
}