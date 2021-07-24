package com.purwasadr.pantaucovid.data

import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.model.Covid
import com.purwasadr.pantaucovid.model.Province
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getCovidData(): Flow<Resource<Covid?>>

    fun getProvinceList(): Flow<Resource<List<Province>>>

    fun getCities(id: String): Flow<Resource<List<CityEntity>>>

    fun getHospitals(provinceId: String, cityId: String): Flow<Resource<List<HospitalEntity>>>
}