package com.purwasadr.pantaucovid.ui.hospital

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.purwasadr.pantaucovid.adapter.DropDownAdapter
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.databinding.FragmentHospitalBinding
import com.purwasadr.pantaucovid.model.City
import com.purwasadr.pantaucovid.model.Province
import com.purwasadr.pantaucovid.ui.hospitallist.HospitalListActivity
import com.purwasadr.pantaucovid.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HospitalFragment : Fragment() {
    private var _binding: FragmentHospitalBinding? = null

    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    private var cityJob: Job? = null

    private val cityAdapter by lazy {
        DropDownAdapter(requireContext())
    }

    private val provinceAdapter by lazy {
        DropDownAdapter(requireContext())
    }

    private var cityList: List<City>? = null

    private var provinceList: List<Province>? = null

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

        setupBtnToHospitalList()
        setupDropDownView()
        getProvince()
    }

    private fun setupBtnToHospitalList() {
        binding.btnToHospitalList.setOnClickListener {
            val provinceId = provinceList!!.get(viewModel.provincePos - 1).id
            val cityId = cityList!!.get(viewModel.cityPos - 1).id

            navigateToHospitalList(provinceId, cityId)
        }
    }

    private fun navigateToHospitalList(provinceId: String, cityId: String) {
        Intent(requireContext(), HospitalListActivity::class.java)
            .putExtra(HospitalListActivity.EXTRA_PROVINCE_ID, provinceId)
            .putExtra(HospitalListActivity.EXTRA_CITY_ID, cityId)
            .also {
                startActivity(it)
            }
    }

    private fun setupDropDownView() {
        if (viewModel.provincePos == 0) {
            dropProvinceDropDown()
            dropCityDropDown()
        }

        binding.actProvince.setAdapter(provinceAdapter)
        binding.actProvince.setOnItemSelected {
            if (it == 0) {
                dropCityDropDown()
                binding.actCity.isEnabled = false
            } else {
                viewModel.provincePos = it
                provinceList?.get(it - 1)?.also { province ->
                    getCities(province.id)
                }
            }
        }

        binding.actCity.setAdapter(cityAdapter)
        binding.actCity.setOnItemSelected {
            viewModel.cityPos = it
            enableBtnToHospitalList()
        }
    }

    private fun enableBtnToHospitalList() {
        binding.btnToHospitalList.isEnabled = viewModel.provincePos != 0 && viewModel.cityPos != 0
    }

    private fun getProvince() {
        lifecycleScope.launch {
            viewModel.province.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.data?.also { data ->
                            updateProvinceDropDown(data)
                        }
                        binding.actProvince.isEnabled = true
                    }

                    is Resource.Loading -> {
                        binding.actProvince.isEnabled = false
                    }
                }
            }
        }
    }

    private fun getCities(id: String) {
        cityJob?.cancel()

        cityJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCities(id).collect {
                when (it) {
                    is Resource.Loading -> {
                        dropCityDropDown()
                        binding.actCity.isEnabled = false
                    }
                    is Resource.Success -> {
                        it.data?.also { data ->
                            updateCityDropDown(data)
                        }
                        binding.actCity.isEnabled = true
                    }
                }
            }
        }
    }

    private fun dropProvinceDropDown() {
        val items = listOf("Belum Memilih")
        provinceAdapter.submitData(items)
        binding.actProvince.selectedPosition = 0
    }

    private fun dropCityDropDown() {
        val items = listOf("Belum Memilih")
        cityAdapter.submitData(items)
        binding.actCity.selectedPosition = 0
    }

    private fun updateCityDropDown(data: List<City>) {
        val items = listOf("Belum Memilih") + data.map { it.name ?: "" }
        cityList = data
        cityAdapter.submitData(items)
    }

    private fun updateProvinceDropDown(data: List<Province>) {
        val items = listOf("Belum Memilih") + data.map { it.name }
        provinceList = data
        provinceAdapter.submitData(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NOT_CHOICE = "not_choice"
    }
}