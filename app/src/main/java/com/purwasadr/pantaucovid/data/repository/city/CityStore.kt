package com.purwasadr.pantaucovid.data.repository.city

import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

class CityStore @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertEntities(entities: List<CityEntity>) {
        database.cityDao().deleteInsert(entities)
    }

    fun getEntities() =
        database.cityDao().getCities()
}