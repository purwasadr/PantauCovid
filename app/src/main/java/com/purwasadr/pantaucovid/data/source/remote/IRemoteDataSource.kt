package com.purwasadr.pantaucovid.data.source.remote

import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CityResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.HospitalResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvinceResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    fun getCovidData(): Flow<ApiResponse<CovidResponse>>

    fun getProvince(): Flow<ApiResponse<ProvinceResponse>>

    fun getCities(id: String): Flow<ApiResponse<CityResponse>>

    fun getHospitals(provinceId: String, cityId: String): Flow<ApiResponse<HospitalResponse>>
}