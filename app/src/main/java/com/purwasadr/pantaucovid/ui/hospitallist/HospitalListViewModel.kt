package com.purwasadr.pantaucovid.ui.hospitallist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.purwasadr.pantaucovid.data.repository.hospital.HospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HospitalListViewModel @Inject constructor(
    private val hospitalRepository: HospitalRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    val provinceId = stateHandle.get<String>(HospitalListActivity.EXTRA_PROVINCE_ID)!!
    val cityId = stateHandle.get<String>(HospitalListActivity.EXTRA_CITY_ID)!!

    val hospitalList = hospitalRepository.getHospitals(provinceId, cityId)
}