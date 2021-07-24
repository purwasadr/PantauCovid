package com.purwasadr.pantaucovid.data.source.local

import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getCovidData(): Flow<CovidEntity?>

    suspend fun insert(covidEntity: CovidEntity)

    fun getProvinceList(): Flow<List<ProvinceHospitalEntity>>

    suspend fun insertProvince(entity: List<ProvinceHospitalEntity>)

    fun getCities(): Flow<List<CityEntity>>

    suspend fun insertCities(entity: List<CityEntity>)
}