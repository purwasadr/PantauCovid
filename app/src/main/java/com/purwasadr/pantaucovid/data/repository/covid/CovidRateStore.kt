package com.purwasadr.pantaucovid.data.repository.covid

import com.purwasadr.pantaucovid.data.source.local.entity.CovidDataEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

class CovidRateStore @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertEntity(entity: CovidDataEntity) {
        database.covidDataDao().insert(entity)
    }

    fun getEntity() = database.covidDataDao().observeEntity()
}