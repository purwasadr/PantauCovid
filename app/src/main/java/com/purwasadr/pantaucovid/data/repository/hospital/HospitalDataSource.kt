package com.purwasadr.pantaucovid.data.repository.hospital

import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import com.purwasadr.pantaucovid.util.flowHandleRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HospitalDataSource @Inject constructor(
    private val service: HospitalService,
    private val defaultDispatcher: CoroutineDispatcher
) {

    fun getHospitals(provinceId: String, cityId: String) =
        flowHandleRequest(defaultDispatcher) { service.getHospitals(provinceId, cityId) }
}