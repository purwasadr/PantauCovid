package com.purwasadr.pantaucovid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.purwasadr.pantaucovid.data.IAppRepository
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IAppRepository
) : ViewModel() {
    val getCovidData = repository.getCovidData().asLiveData()

    val province = repository.getProvinceList().asLiveData()

    var cities: Flow<Resource<List<CityEntity>>>? = null

    var hospitals: Flow<Resource<List<HospitalEntity>>>? = null

    fun getCities(id: String): Flow<Resource<List<CityEntity>>> {

        return repository.getCities(id)
    }

    fun getHospitals(provinceId: String, cityId: String): Flow<Resource<List<HospitalEntity>>> {

        return  repository.getHospitals(provinceId, cityId)
    }
}