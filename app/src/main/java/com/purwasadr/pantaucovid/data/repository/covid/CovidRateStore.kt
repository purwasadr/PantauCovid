package com.purwasadr.pantaucovid.data.repository.covid

import com.purwasadr.pantaucovid.OpenForTesting
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

@OpenForTesting
class CovidRateStore @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertEntity(entity: CovidRateEntity) {
        database.covidDataDao().insert(entity)
    }

    fun getEntity() = database.covidDataDao().observeEntity()
}