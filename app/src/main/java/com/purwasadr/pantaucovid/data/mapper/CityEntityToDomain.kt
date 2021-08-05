package com.purwasadr.pantaucovid.data.mapper

import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.model.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityEntityToDomain @Inject constructor() : Mapper<List<CityEntity>, List<City>> {
    override suspend fun map(from: List<CityEntity>): List<City> =
        from.map {
            City(it.id, it.name)
        }

}