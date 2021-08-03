package com.purwasadr.pantaucovid.data.repository.hospital

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.HospitalResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HospitalRepository @Inject constructor(
    private val dataSource: HospitalDataSource,
    private val store: HospitalStore
) {
    fun getHospitals(
        provinceId: String,
        cityId: String
    ): Flow<Resource<List<HospitalEntity>>> =
        object : NetworkBoundResource<List<HospitalEntity>, HospitalResponse>() {
            override fun loadFromDB(): Flow<List<HospitalEntity>> =
                store.getEntities()

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<HospitalResponse>> =
                dataSource.getHospitals(provinceId, cityId)

            override suspend fun saveCallResult(data: HospitalResponse) {
                data.toEntity()?.also {
                    store.insertEntities(it)
                }

            }

        }.asFlow()
}