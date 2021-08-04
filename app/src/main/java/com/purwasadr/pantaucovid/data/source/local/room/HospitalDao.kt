package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HospitalDao {
    @Query("SELECT * FROM hospital")
    abstract fun getHospitals(): Flow<List<HospitalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(filmDetail: List<HospitalEntity>)

    @Query("DELETE FROM hospital")
    abstract suspend fun deleteAll()
}