package com.purwasadr.pantaucovid.data.repository.province

import com.purwasadr.pantaucovid.OpenForTesting
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

@OpenForTesting
class ProvinceStore @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun delsertEntities(entities: List<ProvinceEntity>) {
        database.provinceDao().delSert(entities)
    }

    fun getEntries() = database.provinceDao().getProvinceList()
}