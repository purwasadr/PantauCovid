package com.purwasadr.pantaucovid.data.repository.covid

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.source.local.entity.CovidDataEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CovidRateRepository @Inject constructor(
    private val dataSource: CovidRateDataSource,
    private val store: CovidRateStore
) {
    fun getCovidRate() = object : NetworkBoundResource<CovidDataEntity?, CovidResponse>() {
        override fun loadFromDB(): Flow<CovidDataEntity?> =
            store.getEntity()

        override fun shouldFetch(): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<CovidResponse>> =
            dataSource.getCovidRate()


        override suspend fun saveCallResult(data: CovidResponse) {
            store.insertEntity(data.toEntity())
        }

    }.asFlow()
}