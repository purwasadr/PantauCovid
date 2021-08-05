package com.purwasadr.pantaucovid.data.repository.city

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.mapper.CityEntityToDomain
import com.purwasadr.pantaucovid.data.mapper.CityResponseToEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CityItemResponse
import com.purwasadr.pantaucovid.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val dataSource: CityDataSource,
    private val store: CityStore,
    private val dataSourceMapper: CityResponseToEntity,
    private val storeMapper: CityEntityToDomain
) {
    fun getCities(provinceId: String) =
        object : NetworkBoundResource<List<City>, List<CityItemResponse>>() {
            override fun loadFromDB(): Flow<List<City>> =
                store.getEntities().map {
                    storeMapper.map(it)
                }

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<CityItemResponse>>> =
                dataSource.getCities(provinceId)

            override suspend fun saveCallResult(data: List<CityItemResponse>) {
                store.insertEntities(dataSourceMapper.map(data))
            }
        }.asFlow()
}