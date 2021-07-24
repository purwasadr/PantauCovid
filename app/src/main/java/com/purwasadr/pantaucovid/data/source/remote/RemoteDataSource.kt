package com.purwasadr.pantaucovid.data.source.remote

import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.network.ApiService
import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
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
}
