package com.purwasadr.pantaucovid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.purwasadr.pantaucovid.data.IAppRepository
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.model.Covid
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
    private val repository: IAppRepository
) : ViewModel() {
    private val _covidData = MutableStateFlow<Resource<Covid?>>(Resource.Loading())

    val covidData: StateFlow<Resource<Covid?>> = _covidData

    private var covidJob: Job? = null

    init {
        covidJob = viewModelScope.launch {
            repository.getCovidData().collect {
                _covidData.value = it
            }
        }.also { it.invokeOnCompletion { Timber.d("collect init complete") } }
    }

    fun refreshCovidData() {
        covidJob?.cancel()
        covidJob = viewModelScope.launch {
            repository.getCovidData().collect {
                _covidData.value = it
            }
        }.also {
            it.invokeOnCompletion {
                Timber.d("collect refresh complete")
            }
        }
    }

    val getCovidData = repository.getCovidData().asLiveData()

    val province = repository.getProvinceList().asLiveData()

    var cities: Flow<Resource<List<CityEntity>>>? = null

    var hospitals: Flow<Resource<List<HospitalEntity>>>? = null

    fun getCities(id: String): Flow<Resource<List<CityEntity>>> {

        return repository.getCities(id)
    }

    fun getHospitals(provinceId: String, cityId: String): Flow<Resource<List<HospitalEntity>>> {

        return repository.getHospitals(provinceId, cityId)
    }
}