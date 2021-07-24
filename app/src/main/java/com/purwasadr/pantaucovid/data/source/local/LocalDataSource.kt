package com.purwasadr.pantaucovid.data.source.local

import androidx.room.withTransaction
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
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

    override fun getProvinceList() = database.provinceDao().getProvinceList()

    override suspend fun insertProvince(entity: List<ProvinceHospitalEntity>) {
        database.provinceDao().insert(entity)
    }

    override fun getCities(): Flow<List<CityEntity>> = database.cityDao().getCities()

    override suspend fun insertCities(entity: List<CityEntity>) {
        database.withTransaction {
            database.cityDao().deleteAll()
            database.cityDao().insert(entity)
        }
    }
}