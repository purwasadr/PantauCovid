package com.purwasadr.pantaucovid.data.repository.city

import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import com.purwasadr.pantaucovid.util.flowHandleRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CityDataSource  @Inject constructor(
    private val apiService: HospitalService,
    private val defaultDispatcher: CoroutineDispatcher
) {

    fun getCities(provinceId: String) = flowHandleRequest(defaultDispatcher) {
        apiService.getCities(provinceId)
    }
}