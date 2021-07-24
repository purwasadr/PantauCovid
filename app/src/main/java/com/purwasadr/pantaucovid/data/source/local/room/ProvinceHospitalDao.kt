package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProvinceHospitalDao {
    @Query("SELECT * FROM province_hospital")
    fun getProvinceList(): Flow<List<ProvinceHospitalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmDetail: List<ProvinceHospitalEntity>)

    @Query("DELETE FROM province_hospital")
    suspend fun deleteAll()
}