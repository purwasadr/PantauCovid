package com.purwasadr.pantaucovid.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.purwasadr.pantaucovid.R
import com.purwasadr.pantaucovid.adapter.HospitalAdapter
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.databinding.FragmentHospitalBinding
import com.purwasadr.pantaucovid.model.Province
import com.purwasadr.pantaucovid.ui.main.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HospitalFragment : Fragment() {
    private var _binding: FragmentHospitalBinding? = null

    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    private var cityJob: Job? = null

    private var hospitalsJob: Job? = null

    private val listAdapter by lazy {
        HospitalAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHospitalBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListHospital()
        getProvince()
    }

    private fun setupListHospital() {
        binding.listHospital.setHasFixedSize(true)
        binding.listHospital.layoutManager = LinearLayoutManager(requireContext())
        binding.listHospital.adapter = listAdapter
    }

    private fun getProvince() {
        viewModel.province.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.also { data ->

                        updateProvinceDropDown(data)
                    }

                }
                is Resource.Loading -> {
                    dropProvinceDropDown()
                    dropCityDropDown()
                }
            }
        }
    }
    private fun dropProvinceDropDown() {
        val items = listOf("Belum Memilih")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, items)
        binding.actProvince.setAdapter(adapter)
        binding.actProvince.setText("Belum Memilih")
    }

    private fun updateProvinceDropDown(data: List<Province>, ) {
        val items = listOf("Belum Memilih") + data.map { it.name }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, items)
        binding.actProvince.setAdapter(adapter)
        binding.actProvince.setOnItemClickListener { parent, view, position, id ->
            Timber.d("Sswwww")
            if (position == 0) return@setOnItemClickListener
            getCities(data.get(position - 1).id)
        }
    }

    private fun getCities(id: String) {
        cityJob?.cancel()

        cityJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCities(id).collect {
                when (it) {
                    is Resource.Loading -> {
                       dropCityDropDown()
                    }
                    is Resource.Success -> {
                        it.data?.also { data ->
                            updateCityDropDown(data, id)

                        }
                    }
                }
            }


        }
    }

    private fun dropCityDropDown() {
        val items = listOf("Belum Memilih")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, items)
        binding.actCity.setAdapter(adapter)
        binding.actCity.setText("Belum Memilih")
        binding.actCity.setOnItemClickListener { parent, view, position, id ->
            Timber.d("Ssowdowwodwodwoodwowowwowowoswwww")
        }
    }

    private fun updateCityDropDown(data: List<CityEntity>, provinceId: String) {
        val items = listOf("Belum Memilih") + data.map { it.name }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, items)
        binding.actCity.setAdapter(adapter)
        binding.actCity.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) return@setOnItemClickListener
            getHospitals(provinceId, data.get(position - 1).id)
        }
    }

    private fun getHospitals(proviceId: String, cityId: String) {
        hospitalsJob?.cancel()

        hospitalsJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getHospitals(proviceId, cityId).collect {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.also { data ->
                            updateListAdapter(data)
                        }

                    }
                }
            }

        }
    }

    private fun updateListAdapter(hospitals: List<HospitalEntity>) {
        listAdapter.submitList(hospitals)
    }
}