package ir.nilgroup.mountain44.base.weatherData

import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather

class WeatherResponse {

    fun ConvResponse(res: CurrentWeather): WeatherData = WeatherData(
        id = res.id,
        all = res.clouds.all,
        dt = res.dt,
        humidity = res.main.humidity,
        icon = res.weather[0].icon,
        lat = res.coord.lat,
        lon = res.coord.lon,
        main = res.weather[0].main,
        name = res.name,
        pressure = res.main.pressure,
        speed = res.wind.speed,
        temp = res.main.temp,
        seaLevel = res.main.seaLevel
    )
}