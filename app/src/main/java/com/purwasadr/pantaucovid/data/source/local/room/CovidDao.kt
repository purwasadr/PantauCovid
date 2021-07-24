package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CovidDao {
    @Query("SELECT * FROM covid LIMIT 1")
    fun getCovidData(): Flow<CovidEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmDetail: CovidEntity)

    @Query("DELETE FROM covid")
    suspend fun deleteAllFilmDetail()
}