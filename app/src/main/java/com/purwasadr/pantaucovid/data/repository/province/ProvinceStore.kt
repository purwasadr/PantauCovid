package com.purwasadr.pantaucovid.data.repository.province

import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

class ProvinceStore @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertEntries(entities: List<ProvinceHospitalEntity>) {
        database.provinceDao().insert(entities)
    }

    fun getEntries() = database.provinceDao().getProvinceList()
}