package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.purwasadr.pantaucovid.data.source.local.entity.CovidDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CovidRateDao : BaseDao<CovidDataEntity> {
    @Query("SELECT * FROM covid_data WHERE id = 0")
    abstract fun observeEntity(): Flow<CovidDataEntity>
}