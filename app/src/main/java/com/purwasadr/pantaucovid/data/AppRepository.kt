package com.purwasadr.pantaucovid.data

import com.purwasadr.pantaucovid.data.source.local.ILocalDataSource
import com.purwasadr.pantaucovid.data.source.local.entity.toDomain
import com.purwasadr.pantaucovid.data.source.remote.IRemoteDataSource
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidDataResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CovidResponse
import com.purwasadr.pantaucovid.data.source.remote.response.toEntity
import com.purwasadr.pantaucovid.model.Covid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.notify
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : IAppRepository{

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
}