package com.purwasadr.pantaucovid.data.mapper

import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity
import com.purwasadr.pantaucovid.data.source.remote.response.ProvincesItemResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProvinceResponseToEntity @Inject constructor() : Mapper<List<ProvincesItemResponse>, List<ProvinceEntity>> {
    override suspend fun map(from: List<ProvincesItemResponse>): List<ProvinceEntity> =
        from.map {
            ProvinceEntity(
                it.id.orEmpty(),
                it.name.orEmpty()
            )
        }
}