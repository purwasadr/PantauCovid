package com.purwasadr.pantaucovid.data.mapper

import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity
import com.purwasadr.pantaucovid.model.Province
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProvinceEntityToDomain @Inject constructor() : Mapper<List<ProvinceEntity>, List<Province>> {
    override suspend fun map(from: List<ProvinceEntity>): List<Province> =
        from.map {
            Province(
                it.id,
                it.name
            )
        }
}