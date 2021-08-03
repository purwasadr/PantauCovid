package com.purwasadr.pantaucovid.data.repository.city

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CityResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val dataSource: CityDataSource,
    private val store: CityStore
) {
   fun getCities(provinceId: String) = object : NetworkBoundResource<List<CityEntity>, CityResponse>() {
        override fun loadFromDB(): Flow<List<CityEntity>> =
            store.getEntities()

        override fun shouldFetch(): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<CityResponse>> =
            dataSource.getCities(provinceId)

        override suspend fun saveCallResult(data: CityResponse) {
            data.toEntity()?.also {
                store.insertEntities(it)
            }
        }
    }.asFlow()
}