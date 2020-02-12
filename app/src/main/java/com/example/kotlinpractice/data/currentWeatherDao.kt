package com.example.kotlinpractice.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinpractice.data.db.entity.CURRENT_WEATHER_ID
import com.example.kotlinpractice.data.db.entity.CurrentWeatherEntry

@Dao
interface currentWeatherDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE )
    fun upset(weatherEntry : CurrentWeatherEntry)


    @Query("Select * from CURRENT_WEATHER where id=$CURRENT_WEATHER_ID")
    fun getCurrentWeather():LiveData<CurrentWeatherEntry>
}