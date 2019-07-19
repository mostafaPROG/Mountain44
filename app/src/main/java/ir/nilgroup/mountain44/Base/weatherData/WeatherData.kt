package ir.nilgroup.mountain44.Base.weatherData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
class WeatherData(
    @ColumnInfo val all: Double,
    @ColumnInfo val dt: Long,
    @ColumnInfo val humidity: Double,
    @ColumnInfo val icon: String,
    @PrimaryKey
    private val id: Long,
    @ColumnInfo
    private val lat: Double,
    @ColumnInfo
    private val lon: Double,
    @ColumnInfo val main: String,
    @ColumnInfo val name: String,
    @ColumnInfo val pressure: Double,
    @ColumnInfo val speed: Double,
    @ColumnInfo val temp: Double,
    val seaLevel: Double
)