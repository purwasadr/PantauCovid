package com.purwasadr.pantaucovid.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity

@Database(
    entities = [CovidEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun covidDao(): CovidDao
}