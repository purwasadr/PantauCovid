package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProvinceDao {
    @Query("SELECT * FROM province")
    abstract fun getProvinceList(): Flow<List<ProvinceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(filmDetail: List<ProvinceEntity>)

    @Query("DELETE FROM province")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun delSert(entity:  List<ProvinceEntity>) {
        deleteAll()
        insert(entity)
    }
}