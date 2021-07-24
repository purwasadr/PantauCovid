package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity

@Database(
    entities = [
        CovidEntity::class,
        ProvinceHospitalEntity::class,
        CityEntity::class,
        HospitalEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun covidDao(): CovidDao

    abstract fun provinceDao(): ProvinceHospitalDao

    abstract fun cityDao(): CityDao

    abstract fun hospitalDao(): HospitalDao
}