package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CityDao {
    @Query("SELECT * FROM city")
    abstract  fun getCities(): Flow<List<CityEntity>>

    @Transaction
    open suspend fun deleteInsert(entities: List<CityEntity>){
        deleteAll()
        insert(entities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: List<CityEntity>)

    @Query("DELETE FROM city")
    abstract suspend fun deleteAll()
}