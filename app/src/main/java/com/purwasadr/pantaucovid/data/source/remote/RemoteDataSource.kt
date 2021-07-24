package com.purwasadr.pantaucovid.data.source.remote

import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.network.ApiService
import com.purwasadr.pantaucovid.data.source.remote.network.HospitalService
import com.purwasadr.pantaucovid.data.source.remote.response.CityResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvinceResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val hospitalService: HospitalService,
    private val defaultDispatchers: CoroutineDispatcher
) : IRemoteDataSource {

    override fun getCovidData(): Flow<ApiResponse<CovidResponse>> = flow {
        try {
            val results = apiService.getCovidData()
            emit(ApiResponse.Success(results))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(defaultDispatchers)

    override fun getProvince(): Flow<ApiResponse<ProvinceResponse>> = flow {
        try {
            val results = hospitalService.getProvince()
            emit(ApiResponse.Success(results))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(defaultDispatchers)

    override fun getCities(id: String): Flow<ApiResponse<CityResponse>> = flow {
        try {
            val results = hospitalService.getCities(id)
            emit(ApiResponse.Success(results))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(defaultDispatchers)


}
