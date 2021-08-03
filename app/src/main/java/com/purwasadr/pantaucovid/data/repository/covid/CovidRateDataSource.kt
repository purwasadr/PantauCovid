package com.purwasadr.pantaucovid.data.repository.covid

import com.purwasadr.pantaucovid.data.source.remote.network.CovidRateService
import com.purwasadr.pantaucovid.util.flowHandleRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CovidRateDataSource @Inject constructor(
    private val service: CovidRateService,
    private val defaultDispatcher: CoroutineDispatcher
) {

    fun getCovidRate() = flowHandleRequest(defaultDispatcher) { service.getCovidRate() }

}