package com.example.kotlinpractice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinpractice.data.currentWeatherDao
import com.example.kotlinpractice.data.db.entity.CurrentWeatherEntry

@Database(entities = [CurrentWeatherEntry::class], version = 1 , exportSchema = false)
@TypeConverters(Converters::class)
abstract class CurrentWeatherDatabase : RoomDatabase() {

    abstract fun getCurrentDao(): currentWeatherDao

    companion object {

        @Volatile
        private var instance: CurrentWeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
        }


        private fun buildDatabase(context:Context) =
             Room.databaseBuilder(context.applicationContext ,
                 CurrentWeatherDatabase::class.java,
                 "forecast.db")
                 .build()
        }


}