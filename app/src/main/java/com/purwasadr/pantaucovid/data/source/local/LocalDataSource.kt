package com.purwasadr.pantaucovid.data.source.local

import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
private val database: AppDatabase
) : ILocalDataSource {

    override fun getCovidData(): Flow<CovidEntity?> = database.covidDao().getCovidData()

    override suspend fun insert(covidEntity: CovidEntity) {
        database.covidDao().insert(covidEntity)
    }
}