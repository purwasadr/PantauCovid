package com.purwasadr.pantaucovid.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.purwasadr.pantaucovid.databinding.ActivityHospitalBinding

class HospitalActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHospitalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}