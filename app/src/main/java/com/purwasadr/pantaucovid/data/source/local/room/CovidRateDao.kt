package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CovidRateDao : BaseDao<CovidRateEntity> {
    @Query("SELECT * FROM covid_rate WHERE id = 0")
    abstract fun observeEntity(): Flow<CovidRateEntity>
}