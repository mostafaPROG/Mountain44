package ir.nilgroup.mountain44.Base.weatherData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
class WeatherData(
    public val all: Double,
    public val dt: Long,
    public val humidity: Double,
    public val icon: String,
    @PrimaryKey
    public val id: Long,
    public val lat: Double,
    public val lon: Double,
    public val main: String,
    public val name: String,
    public val pressure: Double,
    public val speed: Double,
    public val temp: Double,
    public val seaLevel: Double
)