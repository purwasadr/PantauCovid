package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity

@Database(
    entities = [
        CovidRateEntity::class,
        ProvinceEntity::class,
        CityEntity::class,
        HospitalEntity::class,
    ],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun provinceDao(): ProvinceDao

    abstract fun cityDao(): CityDao

    abstract fun hospitalDao(): HospitalDao

    abstract fun covidDataDao(): CovidRateDao
}