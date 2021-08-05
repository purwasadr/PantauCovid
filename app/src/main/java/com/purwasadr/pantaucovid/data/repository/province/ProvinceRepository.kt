package com.purwasadr.pantaucovid.data.repository.province

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.mapper.ProvinceEntityToDomain
import com.purwasadr.pantaucovid.data.mapper.ProvinceResponseToEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvincesItemResponse
import com.purwasadr.pantaucovid.model.Province
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProvinceRepository @Inject constructor(
    private val dataSource: ProvinceDataSource,
    private val store: ProvinceStore,
    private val responseToEntity: ProvinceResponseToEntity,
    private val entityToDomain: ProvinceEntityToDomain
) {
    fun getProvinces() =
        object : NetworkBoundResource<List<Province>, List<ProvincesItemResponse>>() {
            override fun loadFromDB(): Flow<List<Province>> =
                store.getEntries().map {
                    entityToDomain.map(it)
                }

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ProvincesItemResponse>>> =
                dataSource.getProvince()

            override suspend fun saveCallResult(data: List<ProvincesItemResponse>) {
                store.delsertEntities(responseToEntity.map(data))
            }
        }.asFlow()
}