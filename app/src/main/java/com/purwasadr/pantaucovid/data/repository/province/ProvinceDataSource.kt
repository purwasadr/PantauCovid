package com.purwasadr.pantaucovid.data.repository.province

import com.purwasadr.pantaucovid.OpenForTesting
import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import com.purwasadr.pantaucovid.util.flowHandleRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@OpenForTesting
class ProvinceDataSource @Inject constructor(
    private val apiService: HospitalService,
    private val defaultDispatcher: CoroutineDispatcher
) {

    fun getProvince() = flowHandleRequest(defaultDispatcher) {
        apiService.getProvince().provinces.orEmpty()
    }
}