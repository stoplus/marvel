package com.smart.presentation.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.smart.databinding.MainActivityBinding
import com.smart.presentation.api.Router
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router by inject<Router>()
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        router.attach(binding.navHostFragment.findNavController())
    }
}