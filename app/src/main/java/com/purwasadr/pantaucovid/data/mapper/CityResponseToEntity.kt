package com.purwasadr.pantaucovid.data.mapper

import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.remote.response.CityItemResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityResponseToEntity @Inject constructor() : Mapper<List<CityItemResponse>, List<CityEntity>> {
    override suspend fun map(from: List<CityItemResponse>): List<CityEntity> =
        from.map {
            CityEntity(it.id.orEmpty(), it.name.orEmpty())
        }

}