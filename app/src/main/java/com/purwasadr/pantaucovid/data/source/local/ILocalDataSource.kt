package com.purwasadr.pantaucovid.data.source.local

import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getCovidData(): Flow<CovidEntity?>

    suspend fun insert(covidEntity: CovidEntity)
}