package com.purwasadr.pantaucovid.ui.hospitallist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.purwasadr.pantaucovid.databinding.ActivityHospitalListBinding

class HospitalListActivity : AppCompatActivity() {
    private val binding: ActivityHospitalListBinding by lazy {
        ActivityHospitalListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}