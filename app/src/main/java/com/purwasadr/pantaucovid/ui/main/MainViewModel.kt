package com.purwasadr.pantaucovid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.repository.city.CityRepository
import com.purwasadr.pantaucovid.data.repository.covid.CovidRateRepository
import com.purwasadr.pantaucovid.data.repository.hospital.HospitalRepository
import com.purwasadr.pantaucovid.data.repository.province.ProvinceRepository
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val covidRateRepository: CovidRateRepository,
    private val provinceRepository: ProvinceRepository,
    private val cityRepository: CityRepository,
    private val hospitalRepository: HospitalRepository
) : ViewModel() {
    private val _covidData = MutableStateFlow<Resource<CovidRateEntity?>>(Resource.Loading())

    val covidData: StateFlow<Resource<CovidRateEntity?>> = _covidData

    var provincePos: Int = 0

    var cityPos: Int = 0

    private var covidJob: Job? = null

    init {
        covidJob = viewModelScope.launch {
            covidRateRepository.getCovidRate().collect {
                _covidData.value = it
            }
        }.also { it.invokeOnCompletion { Timber.d("collect init complete") } }
    }

    fun refreshCovidData() {
        covidJob?.cancel()
        covidJob = viewModelScope.launch {
            covidRateRepository.getCovidRate().collect {
                _covidData.value = it
            }
        }.also {
            it.invokeOnCompletion {
                Timber.d("collect refresh complete")
            }
        }
    }

    val province = provinceRepository.getProvinces()

    var cities: Flow<Resource<List<CityEntity>>>? = null

    var hospitals: Flow<Resource<List<HospitalEntity>>>? = null

    fun getCities(id: String): Flow<Resource<List<City>>> {

        return cityRepository.getCities(id)
    }

    fun getHospitals(provinceId: String, cityId: String): Flow<Resource<List<HospitalEntity>>> {

        return hospitalRepository.getHospitals(provinceId, cityId)
    }
}