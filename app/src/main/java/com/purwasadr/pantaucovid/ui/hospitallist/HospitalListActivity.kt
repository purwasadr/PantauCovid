package com.purwasadr.pantaucovid.ui.hospitallist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.purwasadr.pantaucovid.adapter.HospitalAdapter
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.databinding.ActivityHospitalListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HospitalListActivity : AppCompatActivity() {
    private val binding: ActivityHospitalListBinding by lazy {
        ActivityHospitalListBinding.inflate(layoutInflater)
    }

    private val adapter by lazy { HospitalAdapter() }

    private val viewModel: HospitalListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        getHospitalList()
    }

    private fun setupRecyclerView() {
        binding.listHospital.setHasFixedSize(true)
        binding.listHospital.layoutManager = LinearLayoutManager(applicationContext)
        binding.listHospital.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            title = "Daftar Rumah Sakit"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getHospitalList() {
        lifecycleScope.launch {
            viewModel.hospitalList.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.data?.also { data ->
                            adapter.submitList(data)
                        }

                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_PROVINCE_ID = "extra_province_id"
        const val EXTRA_CITY_ID = "extra_city_id"
    }
}