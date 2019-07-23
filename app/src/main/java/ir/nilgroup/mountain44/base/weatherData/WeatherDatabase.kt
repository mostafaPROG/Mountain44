package ir.nilgroup.mountain44.base.weatherData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}