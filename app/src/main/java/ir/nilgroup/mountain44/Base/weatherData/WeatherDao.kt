package ir.nilgroup.mountain44.Base.weatherData

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Dao
interface WeatherDao {
    @Delete
    fun delete(@NotNull weatherData: WeatherData)

    @NotNull
    @Query("select * from weather")
    fun getAll(): List<WeatherData>

    @Insert
    fun insertAll(@NotNull vararg weatherData: WeatherData)

    @NotNull
    @Query("SELECT * FROM weather WHERE id == :userId ")
    fun loadAllByIds(@NotNull userId: IntArray): List<WeatherData>

    @Update
    fun update(@NotNull weatherData: WeatherData)
}