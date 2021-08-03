package com.purwasadr.pantaucovid.data.repository.province

import com.purwasadr.pantaucovid.data.NetworkBoundResource
import com.purwasadr.pantaucovid.data.source.local.entity.toDomain
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvinceResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import com.purwasadr.pantaucovid.model.Province
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProvinceRepository @Inject constructor(
    private val dataSource: ProvinceDataSource,
    private val store: ProvinceStore,
//    private val dispatchers: CoroutineDispatcher
) {
    fun getProvinces() =
        object : NetworkBoundResource<List<Province>, ProvinceResponse>() {
            override fun loadFromDB(): Flow<List<Province>> =
                store.getEntries().map {
                    it.toDomain()
                }

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ProvinceResponse>> =
                dataSource.getProvince()

            override suspend fun saveCallResult(data: ProvinceResponse) {
                data.toEntity()?.also {
                    store.insertEntries(it)
                }
            }
        }.asFlow()
}