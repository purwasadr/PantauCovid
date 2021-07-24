package com.purwasadr.pantaucovid.data.source.remote

import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    fun getCovidData(): Flow<ApiResponse<CovidResponse>>
}