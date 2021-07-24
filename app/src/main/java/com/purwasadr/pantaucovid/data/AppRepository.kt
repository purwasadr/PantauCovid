package com.purwasadr.pantaucovid.data

import com.purwasadr.pantaucovid.data.source.local.ILocalDataSource
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.toDomain
import com.purwasadr.pantaucovid.data.source.remote.IRemoteDataSource
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CityResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvinceResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import com.purwasadr.pantaucovid.model.Covid
import com.purwasadr.pantaucovid.model.Province
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : IAppRepository {

    override fun getCovidData() = object : NetworkBoundResource<Covid?, CovidResponse>() {
        override fun loadFromDB(): Flow<Covid?> =
            localDataSource.getCovidData().map {
                it?.toDomain()
            }

        override fun shouldFetch(): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<CovidResponse>> =
            remoteDataSource.getCovidData()


        override suspend fun saveCallResult(data: CovidResponse) {
            localDataSource.insert(data.toEntity())
        }

    }.asFlow()

    override fun getProvinceList(): Flow<Resource<List<Province>>> =
        object : NetworkBoundResource<List<Province>, ProvinceResponse>() {
            override fun loadFromDB(): Flow<List<Province>> =
                localDataSource.getProvinceList().map {
                    it.toDomain()
                }

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ProvinceResponse>> =
                remoteDataSource.getProvince()

            override suspend fun saveCallResult(data: ProvinceResponse) {
                data.toEntity()?.also {
                    localDataSource.insertProvince(it)
                }
            }
        }.asFlow()

    override fun getCities(id: String): Flow<Resource<List<CityEntity>>> =
        object : NetworkBoundResource<List<CityEntity>, CityResponse>() {
            override fun loadFromDB(): Flow<List<CityEntity>> =
                localDataSource.getCities()

            override fun shouldFetch(): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<CityResponse>> =
                remoteDataSource.getCities(id)

            override suspend fun saveCallResult(data: CityResponse) {
                data.toEntity()?.also {
                    localDataSource.insertCities(it)
                }
            }
        }.asFlow()

}