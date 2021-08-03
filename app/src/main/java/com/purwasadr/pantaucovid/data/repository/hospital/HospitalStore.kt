package com.purwasadr.pantaucovid.data.repository.hospital

import androidx.room.withTransaction
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.data.source.local.room.AppDatabase
import javax.inject.Inject

class HospitalStore @Inject constructor(
    private val database: AppDatabase
) {

    suspend fun insertEntities(entities: List<HospitalEntity>) {
        database.withTransaction {
            database.hospitalDao().deleteAll()
            database.hospitalDao().insert(entities)
        }
    }

    fun getEntities() =
        database.hospitalDao().getHospitals()
}